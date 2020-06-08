package canon.parser.xml.upgrade.xslt


import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import java.nio.charset.StandardCharsets
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

internal class XSLTTransformerTest {

    val WHITE_SPACE_REGEX= "\\s".toRegex()
    val TRANSFORMER_2_0_0 = XSLTTransformer(XSLTTransformerConfiguration(("/xml/xslt/transformers/transform_2.0.0.xslt")))
    val TRANSFORMER_2_2_0 = XSLTTransformer(XSLTTransformerConfiguration(("/xml/xslt/transformers/transform_2.2.0.xslt")))


    private fun transformFromFileSystem(transformer: XSLTTransformer, pathToXMLToTransform: String, pathToExpectedXML: String, expectSuccess: Boolean = true) {
        try {

            val xmlToTransform= XSLTTransformerTest::class.java.getResourceAsStream(pathToXMLToTransform).reader(StandardCharsets.UTF_8).readText()
            val result = transformer.execute(xmlToTransform)
            val resultInRaw=result.replace(WHITE_SPACE_REGEX, "")
            val expectedResultInRaw = XSLTTransformerTest::class.java.getResourceAsStream(pathToExpectedXML).reader(StandardCharsets.UTF_8).readText().replace(WHITE_SPACE_REGEX, "")
            Assertions.assertThat(resultInRaw).isEqualTo(expectedResultInRaw)
        } catch (e: Exception) {
            if (expectSuccess) {
                Assertions.fail<String>("xml '$pathToXMLToTransform' should not throw a transformation exception", e)
            }
        }
    }

    private fun transformString(transformer: XSLTTransformer, xmlToTransform: String, expectedXml: String, expectSuccess: Boolean = true) {
        try {
            val result = transformer.execute(xmlToTransform)
            Assertions.assertThat(normalizeXmlForTestComparison(result)).isEqualTo(normalizeXmlForTestComparison(expectedXml))
        } catch (e: Exception) {
            if (expectSuccess) {
                Assertions.fail<String>("xml '$expectedXml' should not throw a transformation exception", e)
            }
        }
    }

    private fun normalizeXmlForTestComparison(text : String) = text.trimIndent().trimMargin().trim().replace("\n", "").replace("\t", "")


    @Test
    fun complexXmlIsParsed() {
        transformFromFileSystem(TRANSFORMER_2_0_0, "/xml/xslt/complex1.xml","/xml/xslt/expected/complex1.xml")
        transformFromFileSystem(TRANSFORMER_2_2_0, "/xml/xslt/complex2.xml","/xml/xslt/expected/complex2.xml")
    }

    @Test
    fun `Transform keeps the format`(){
        val input="""<italic>VALUE</italic>
<video src="src" />
<text id="123">Basierend auf Ihren Angaben können wir Ihnen folgende {result.size()} Resultate vorschlagen:</text>
<text>    </text>
<button value="a" name="b">Text</button>
<carousel>
<block foreach="lawyer in result">
    <headline>{{lawyer.name}}</headline>
    <text>{lawyer.address}</text>
    <text></text>
    <textInput name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text" />
    <items>
        <item>Telefon: {lawyer.phone}</item>
        <item>Email: {lawyer.email}</item>
    </items>
    <text>Tätigkeitsgebiete</text>
    <items>
        <item>abc</item>
        <item>
            <items>
                <item>abc</item>
                <item>abc</item>
                <item>abc</item>
            </items>
        </item>
    </items>
    <items>
        <item foreach="topic in lawyer.topics">topic</item>
    </items>
    <text>Sprachen</text>
    <items>
        <item foreach="type in lawyer.languages">type</item>
    </items>
    <checkbox name="a" value="b">Text</checkbox>
</block>
</carousel>
<image src="a" width="100" height="100" alt="b" />
<link value="a">b</link>
<headline>{lawyer.name}</headline>
<block>
<image src="a" width="100" height="100" alt="b" />
<link value="a">b</link>
<checkbox name="a" value="b">Text</checkbox>
<headline>{lawyer.name}</headline>
<textarea name="ta" value="abc" cols="1" rows="1" />
<slider value="3" min="1" max="10" step="1" name="{{foo}}" />
<slotmachine>
    <reel name="name" class="abc">
        <reelValue valueType="digit" value="3" />
    </reel>
</slotmachine>
<checkbox name="a" value="b">Text</checkbox>
</block>
<block>
<form>
    <checkbox name="hello" value="world" />
    <checkbox name="hello" value="world" />
</form>
<block>
    <checkbox name="hello" value="world" />
    <checkbox name="hello" value="world" />
</block>
<block>
    <checkbox name="hello" value="world" />
    <block>
        <checkbox name="hello" value="world" />
    </block>
</block>
<form>
    <table>
        <row>
            <col>
                <checkbox name="hello" value="world" />
            </col>
            <col>
                <checkbox name="hello" value="world" />
            </col>
        </row>
        <row>
            <col>
                <checkbox name="hello" value="world" />
            </col>
            <col>
                <checkbox name="ingredients" class="checkbox" id="checkbox" value="ananas" checked="true">Ananas</checkbox>
            </col>
        </row>
    </table>
</form>
</block>"""
        val exptected="""<italic>VALUE</italic>
<video src="src"/>
<label id="123">Basierend auf Ihren Angaben können wir Ihnen folgende {result.size()} Resultate vorschlagen:</label>
<label>    </label>
<button value="a" name="b">Text</button>
<carousel>
<block foreach="lawyer in result">
    <headline>{{lawyer.name}}</headline>
    <label>{lawyer.address}</label>
    <label/>
    <text name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
    <items>
        <item>Telefon: {lawyer.phone}</item>
        <item>Email: {lawyer.email}</item>
    </items>
    <label>Tätigkeitsgebiete</label>
    <items>
        <item>abc</item>
        <item>
            <items>
                <item>abc</item>
                <item>abc</item>
                <item>abc</item>
            </items>
        </item>
    </items>
    <items>
        <item foreach="topic in lawyer.topics">topic</item>
    </items>
    <label>Sprachen</label>
    <items>
        <item foreach="type in lawyer.languages">type</item>
    </items>
    <!--manual action needed--><!--use choice instead of checkbox: <checkbox name="a" value="b">Text</checkbox>-->
</block>
</carousel>
<image src="a" width="100" height="100" alt="b"/>
<link value="a">b</link>
<headline>{lawyer.name}</headline>
<block>
<image src="a" width="100" height="100" alt="b"/>
<link value="a">b</link>
<!--manual action needed--><!--use choice instead of checkbox: <checkbox name="a" value="b">Text</checkbox>-->
<headline>{lawyer.name}</headline>
<textarea name="ta" value="abc" cols="1" rows="1"/>
<slider value="3" min="1" max="10" step="1" name="{{foo}}"/>
<slotmachine>
    <reel name="name" class="abc">
        <reelValue valueType="digit" value="3"/>
    </reel>
</slotmachine>
<!--manual action needed--><!--use choice instead of checkbox: <checkbox name="a" value="b">Text</checkbox>-->
</block>
<block>
<form>
    <!--manual action needed--><!--use choice instead of checkbox: <checkbox name="hello" value="world"/>-->
    <!--manual action needed--><!--use choice instead of checkbox: <checkbox name="hello" value="world"/>-->
</form>
<block>
    <!--manual action needed--><!--use choice instead of checkbox: <checkbox name="hello" value="world"/>-->
    <!--manual action needed--><!--use choice instead of checkbox: <checkbox name="hello" value="world"/>-->
</block>
<block>
    <!--manual action needed--><!--use choice instead of checkbox: <checkbox name="hello" value="world"/>-->
    <block>
        <!--manual action needed--><!--use choice instead of checkbox: <checkbox name="hello" value="world"/>-->
    </block>
</block>
<form>
    <table>
        <row>
            <col>
                <!--manual action needed--><!--use choice instead of checkbox: <checkbox name="hello" value="world"/>-->
            </col>
            <col>
                <!--manual action needed--><!--use choice instead of checkbox: <checkbox name="hello" value="world"/>-->
            </col>
        </row>
        <row>
            <col>
                <!--manual action needed--><!--use choice instead of checkbox: <checkbox name="hello" value="world"/>-->
            </col>
            <col>
                <!--manual action needed--><!--use choice instead of checkbox: <checkbox name="ingredients" class="checkbox" id="checkbox" value="ananas" checked="true">Ananas</checkbox>-->
            </col>
        </row>
    </table>
</form>
</block>"""

        val result= TRANSFORMER_2_0_0.execute(input)
        Assertions.assertThat(result).isEqualTo(exptected)

    }

    @TestFactory
    fun `Transform XML with XSLT 2_0_0`() = listOf(
            /*****************************************TEXT TRANSFORMATIONS ******************************************************/

            mapOf("name" to "Simple text tag is transformed to a label",
                    "givenXml" to """                   <text>text</text>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        
                                                        <label>text</label>
                                                        
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "Simple text tag is transformed to a label and empty spaces are kept",
                "givenXml" to """                       <text>  </text>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <label>  </label>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "Text tag with attribute id and value is transformed to a label",
                    "givenXml" to """
                                                       <text id="abc">text</text>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <label id="abc">text</label>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "Text tag with attribute id, class and if and value is transformed to a label",
                    "givenXml" to """
                                                       <text id="abc" class=".cs" if="(aaa)">text</text>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <label id="abc" class=".cs" if="(aaa)">text</label>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "Text tag with attribute id, class, if , name and value is transformed to a label",
                    "givenXml" to """
                                                       <text id="abc" name="textname" class=".cs" if="(aaa)">text</text>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <label id="abc" class=".cs" if="(aaa)">text</label>
                                                """.trimIndent().trimMargin().trim()),
            /*****************************************CALENDAR TRANSFORMATIONS ******************************************************/
            mapOf("name" to "Calendar tag without attributes is removed_1",
                    "givenXml" to """
                                                       <calendar></calendar>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!--manual action needed--><!--calendar element was removed-->
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "Calendar tag without attributes is removed_2",
                    "givenXml" to """
                                                       <calendar/>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!--manual action needed--><!--calendar element was removed-->
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "Calendar tag with attribute id, class and name is removed",
                    "givenXml" to """
                                                       <calendar id="testId" class="testClass" name="testName"></calendar>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!--manual action needed--><!--calendar element was removed-->
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "Calendar tag with attribute id and name removed",
                    "givenXml" to """
                                                       <calendar id="testId" name="testName"></calendar>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!--manual action needed--><!--calendar element was removed-->
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "Calendar tag with attribute id and class is removed",
                    "givenXml" to """
                                                       <calendar id="testId" class="testClass"></calendar>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!--manual action needed--><!--calendar element was removed-->
                                                """.trimIndent().trimMargin().trim()),

            /*****************************************DATEPICKER TRANSFORMATIONS ******************************************************/
            mapOf("name" to "DatePicker tag without attributes is removed_1",
                    "givenXml" to """
                                                       <datePicker></datePicker>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!--manual action needed--><!--datePicker element was removed-->
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "DatePicker tag without attributes is removed_2",
                    "givenXml" to """
                                                       <datePicker/>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!--manual action needed--><!--datePicker element was removed-->
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "DatePicker tag with attribute source and size is removed",
                    "givenXml" to """
                                                       <datePicker src="SOME ICAL STRING" size="0"></datePicker>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!--manual action needed--><!--datePicker element was removed-->
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "DatePicker tag with attribute source removed",
                    "givenXml" to """
                                                       <datePicker src="SOME ICAL STRING"></datePicker>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!--manual action needed--><!--datePicker element was removed-->
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "DatePicker tag with attribute size is removed",
                    "givenXml" to """
                                                       <datePicker size="0"></datePicker>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!--manual action needed--><!--datePicker element was removed-->
                                                """.trimIndent().trimMargin().trim()),

            /*****************************************DATETIMEPICKER TRANSFORMATIONS ******************************************************/
            mapOf("name" to "DateTimePicker tag without attributes is removed_1",
                    "givenXml" to """
                                                       <dateTimePicker></dateTimePicker>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!--manual action needed--><!--dateTimePicker element was removed-->
                                                        
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "DateTimePicker tag without attributes is removed_2",
                    "givenXml" to """
                                                       <dateTimePicker/>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!--manual action needed--><!--dateTimePicker element was removed-->
                                                        
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "DateTimePicker tag with attribute source and size is removed",
                    "givenXml" to """
                                                       <dateTimePicker src="SOME ICAL STRING" size="0"></dateTimePicker>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!--manual action needed--><!--dateTimePicker element was removed-->
                                                        
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "DateTimePicker tag with attribute source removed",
                    "givenXml" to """
                                                       <dateTimePicker src="SOME ICAL STRING"></dateTimePicker>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!--manual action needed--><!--dateTimePicker element was removed-->
                                                        
                                                    """.trimIndent().trimMargin().trim()),
            mapOf("name" to "DateTimePicker tag with attribute size is removed",
                    "givenXml" to """
                                                       <dateTimePicker size="0"></dateTimePicker>
                                                   """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!--manual action needed--><!--dateTimePicker element was removed-->
                                                        
                                                    """.trimIndent().trimMargin().trim()),

            /*****************************************TEXTINPUT TRANSFORMATIONS ******************************************************/

            mapOf("name" to "TextInput tag with attribute id is transformed to a text",
                    "givenXml" to """
                                                       <textInput id="abc"/>
                                                    """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        
                                                        <text id="abc"/>
                                                        
                                                    """.trimIndent().trimMargin().trim()),
            mapOf("name" to "TextInput tag with attributes name, placeholder, regex, value, required and class is transformed to a text",
                    "givenXml" to """
                                                       <textInput name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                                    """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        
                                                        <text name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                                        
                                                    """.trimIndent()),
            /*****************************************ATREUS EXPRESSION TRANSFORMATIONS ******************************************************/
            mapOf("name" to "atreus expression with single quotes can me migrated",
                    "givenXml" to """
                                                       <text if="{{eq(${'$'}someContextVar,'2')}}">this is the text</text>
                                                    """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <label if="{{eq(${'$'}someContextVar,'2')}}">this is the text</label>
                                                    """.trimIndent()),
            mapOf("name" to "atreus expression with double quotes can me migrated",
                    "givenXml" to """
                                                       <text if='{{eq(${'$'}someContextVar,"2")}}'>this is the text</text>
                                                    """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <label if="{{eq(${'$'}someContextVar,&quot;2&quot;)}}">this is the text</label>
                                                    """.trimIndent()),
            mapOf("name" to "atreus expression with double quotes can me migrated",
                    "givenXml" to """
                                                       <block if='{{or(isNull(${'$'}euCivic?), eq(${'$'}employmentPermission?, "yes"), and(eq(${'$'}euCivic?, "no"), isNull(${'$'}employmentPermission?) ) )}}' class="row no-gutters justify-content-center employment-permission">
                                                        <block class="col">
                                                            <block class="row">
                                                                <text class="bold uppercase">Deine Beschäftigungspapiere</text>
                                                            </block>
                                                            <block class="row">
                                                                <block class="col">
                                                                    <upload maxCompressSize="1" maxSize="5" name="employment_permission_upload" accept="pdf,jpg,jpeg,png"></upload>
                                                                </block>
                                                                <block if="{{eq(${'$'}isMobile, false)}}" class="col">
                                                                    <trigger name="employment_permission"></trigger>
                                                                </block>
                                                            </block>
                                                            <block class="row">
                                                                <block class="col">
                                                                    <text>Datei hochladen</text>
                                                                </block>
                                                                <block if="{{eq(${'$'}isMobile, false)}}" class="col">
                                                                    <text>Foto machen</text>
                                                                </block>
                                                            </block>
                                                        </block>
                                                    </block>
                                                    """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <block if="{{or(isNull(${'$'}euCivic?), eq(${'$'}employmentPermission?, &quot;yes&quot;), and(eq(${'$'}euCivic?, &quot;no&quot;), isNull(${'$'}employmentPermission?) ) )}}" class="row no-gutters justify-content-center employment-permission">
                                                            <block class="col">
                                                                <block class="row">
                                                                    <label class="bold uppercase">Deine Beschäftigungspapiere</label>
                                                                </block>
                                                                <block class="row">
                                                                    <block class="col">
                                                                        <upload maxCompressSize="1" maxSize="5" name="employment_permission_upload" accept="pdf,jpg,jpeg,png"/>
                                                                    </block>
                                                                    <block if="{{eq(${'$'}isMobile, false)}}" class="col">
                                                                        <trigger name="employment_permission"/>
                                                                    </block>
                                                                </block>
                                                                <block class="row">
                                                                    <block class="col">
                                                                        <label>Datei hochladen</label>
                                                                    </block>
                                                                    <block if="{{eq(${'$'}isMobile, false)}}" class="col">
                                                                        <label>Foto machen</label>
                                                                    </block>
                                                                </block>
                                                            </block>
                                                        </block>
                                                    """.trimIndent())
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} given XML: [${it["givenXml"]}] -> ${it["expectedTransformation"]}") {

            transformString(TRANSFORMER_2_0_0, it["givenXml"] as String ,it["expectedTransformation"] as String)
        }
    }


    @TestFactory
    fun `Transform XML with XSLT 2_2_0`() = listOf(
            mapOf("name" to "Simple text tag is transformed to a label",
                    "givenXml" to """
                                                        <block>text</block>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <block>text</block>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "Simple text tag is transformed to a label",
                    "givenXml" to """
                                                        <selection>
                                                            <block>text</block>
                                                        </selection>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <selection>
                                                            <selectionItem>text</selectionItem>
                                                        </selection>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "Simple text tag is transformed to a label",
                    "givenXml" to """
                                                        <selection>
                                                            <block>
                                                                <block>text</block>
                                                            </block>
                                                        </selection>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <selection>
                                                            <selectionItem>
                                                                <block>text</block>
                                                            </selectionItem>
                                                        </selection>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "Simple text tag is transformed to a label",
                    "givenXml" to """
                                                        <selection>
                                                            <block>
                                                                <block>
                                                                    <block>text</block>
                                                                </block>
                                                            </block>
                                                        </selection>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <selection>
                                                            <selectionItem>
                                                                <block>
                                                                    <selectable>text</selectable>
                                                                </block>
                                                            </selectionItem>
                                                        </selection>
                                                """.trimIndent().trimMargin().trim())
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} given XML: [${it["givenXml"]}] -> ${it["expectedTransformation"]}") {

            transformString(TRANSFORMER_2_2_0, it["givenXml"] as String, it["expectedTransformation"] as String)
        }
    }

    @Test
    fun parallelTest() {
        val barrier = CountDownLatch(1)
        val endBarrier = CountDownLatch(25)
        (0..24).forEach {
            Thread {
                try{
                    barrier.await(10, TimeUnit.SECONDS)
                    println("Thread-${it} starting execution")
                    transformFromFileSystem(TRANSFORMER_2_0_0,"/xml/xslt/complex1.xml","/xml/xslt/expected/complex1.xml")
                } finally {
                    endBarrier.countDown()
                }
            }.start()
        }
        barrier.countDown()
        endBarrier.await(60, TimeUnit.SECONDS)
    }
}