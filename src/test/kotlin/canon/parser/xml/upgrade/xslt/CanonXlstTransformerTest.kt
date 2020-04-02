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

    private fun transformFromFileSystem(transformer: XSLTTransformer, pathToXMLToTransform: String, pathToExpectedXML: String, expectSuccess: Boolean = true) {
        try {

            val xmlToTransform= XSLTTransformerTest::class.java.getResourceAsStream(pathToXMLToTransform).reader(StandardCharsets.UTF_8).readText()
            val result = transformer.execute(xmlToTransform)
//            val resultInRaw=result.replace(WHITE_SPACE_REGEX, "")
            val expectedResultInRaw = XSLTTransformerTest::class.java.getResourceAsStream(pathToExpectedXML).reader(StandardCharsets.UTF_8).readText()//.replace(WHITE_SPACE_REGEX, "")
            Assertions.assertThat(result).isEqualTo(expectedResultInRaw)
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
    fun complexXmlIsParsed() = transformFromFileSystem(TRANSFORMER_2_0_0, "/xml/xslt/complex1.xml","/xml/xslt/expected/complex1.xml")

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
                <checkbox name="hello" value="world" />
            </col>
        </row>
    </table>
</form>
</block>"""
        val exptected="""<italic>VALUE</italic>
<video src="src"/>
<!-- text element was replaced with label--><label id="123">Basierend auf Ihren Angaben können wir Ihnen folgende {result.size()} Resultate vorschlagen:</label><automaticUpgraded/>
<!-- text element was replaced with label--><label>    </label><automaticUpgraded/>
<button value="a" name="b">Text</button>
<carousel>
<block foreach="lawyer in result">
    <headline>{{lawyer.name}}</headline>
    <!-- text element was replaced with label--><label>{lawyer.address}</label><automaticUpgraded/>
    <!-- text element was replaced with label--><label/><automaticUpgraded/>
    <!-- textInput element was replaced with text--><text name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/><automaticUpgraded/>
    <items>
        <item>Telefon: {lawyer.phone}</item>
        <item>Email: {lawyer.email}</item>
    </items>
    <!-- text element was replaced with label--><label>Tätigkeitsgebiete</label><automaticUpgraded/>
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
    <!-- text element was replaced with label--><label>Sprachen</label><automaticUpgraded/>
    <items>
        <item foreach="type in lawyer.languages">type</item>
    </items>
    <!--Checkbox element was commented out-->
            <!-- before migration: <checkbox name="a" value="b">Text</checkbox> -->
</block>
</carousel>
<image src="a" width="100" height="100" alt="b"/>
<link value="a">b</link>
<headline>{lawyer.name}</headline>
<block>
<image src="a" width="100" height="100" alt="b"/>
<link value="a">b</link>
<!--Checkbox element was commented out-->
            <!-- before migration: <checkbox name="a" value="b">Text</checkbox> -->
<headline>{lawyer.name}</headline>
<textarea name="ta" value="abc" cols="1" rows="1"/>
<slider value="3" min="1" max="10" step="1" name="{{foo}}"/>
<slotmachine>
    <reel name="name" class="abc">
        <reelValue valueType="digit" value="3"/>
    </reel>
</slotmachine>
<!--Checkbox element was commented out-->
            <!-- before migration: <checkbox name="a" value="b">Text</checkbox> -->
</block>
<block>
<form>
    <!--Checkbox element was commented out-->
            <!-- before migration: <checkbox name="hello" value="world"/> -->
    <!--Checkbox element was commented out-->
            <!-- before migration: <checkbox name="hello" value="world"/> -->
</form>
<block>
    <!--Checkbox element was commented out-->
            <!-- before migration: <checkbox name="hello" value="world"/> -->
    <!--Checkbox element was commented out-->
            <!-- before migration: <checkbox name="hello" value="world"/> -->
</block>
<block>
    <!--Checkbox element was commented out-->
            <!-- before migration: <checkbox name="hello" value="world"/> -->
    <block>
        <!--Checkbox element was commented out-->
            <!-- before migration: <checkbox name="hello" value="world"/> -->
    </block>
</block>
<form>
    <table>
        <row>
            <col>
                <!--Checkbox element was commented out-->
            <!-- before migration: <checkbox name="hello" value="world"/> -->
            </col>
            <col>
                <!--Checkbox element was commented out-->
            <!-- before migration: <checkbox name="hello" value="world"/> -->
            </col>
        </row>
        <row>
            <col>
                <!--Checkbox element was commented out-->
            <!-- before migration: <checkbox name="hello" value="world"/> -->
            </col>
            <col>
                <!--Checkbox element was commented out-->
            <!-- before migration: <checkbox name="hello" value="world"/> -->
            </col>
        </row>
    </table>
</form>
</block>"""

        val result= TRANSFORMER_2_0_0.execute(input)
        Assertions.assertThat(result).isEqualTo(exptected)

    }

    @TestFactory
    fun `Transform XML with XSLT`() = listOf(
            /*****************************************TEXT TRANSFORMATIONS ******************************************************/

            mapOf("name" to "Simple text tag is transformed to a label",
                    "givenXml" to """                   <text>text</text>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- text element was replaced with label-->
                                                        <label>text</label>
                                                        <automaticUpgraded/>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "Simple text tag is transformed to a label and empty spaces are kept",
                "givenXml" to """                       <text>  </text>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- text element was replaced with label-->
                                                        <label>  </label>
                                                        <automaticUpgraded/>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "Text tag with attribute id and value is transformed to a label",
                    "givenXml" to """
                                                       <text id="abc">text</text>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- text element was replaced with label-->
                                                        <label id="abc">text</label>
                                                        <automaticUpgraded/>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "Text tag with attribute id, class and if and value is transformed to a label",
                    "givenXml" to """
                                                       <text id="abc" class=".cs" if="(aaa)">text</text>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- text element was replaced with label-->
                                                        <label id="abc" class=".cs" if="(aaa)">text</label>
                                                        <automaticUpgraded/>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "Text tag with attribute id, class, if , name and value is transformed to a label",
                    "givenXml" to """
                                                       <text id="abc" name="textname" class=".cs" if="(aaa)">text</text>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- text element was replaced with label-->
                                                        <!--attribute name of element text was removed when converting it to label-->
                                                        <label id="abc" class=".cs" if="(aaa)">text</label>
                                                        <automaticUpgraded/>
                                                """.trimIndent().trimMargin().trim()),
            /*****************************************CALENDAR TRANSFORMATIONS ******************************************************/
            mapOf("name" to "Calendar tag without attributes is removed_1",
                    "givenXml" to """
                                                       <calendar></calendar>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- Calendar element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "Calendar tag without attributes is removed_2",
                    "givenXml" to """
                                                       <calendar/>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- Calendar element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "Calendar tag with attribute id, class and name is removed",
                    "givenXml" to """
                                                       <calendar id="testId" class="testClass" name="testName"></calendar>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- Calendar element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "Calendar tag with attribute id and name removed",
                    "givenXml" to """
                                                       <calendar id="testId" name="testName"></calendar>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- Calendar element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "Calendar tag with attribute id and class is removed",
                    "givenXml" to """
                                                       <calendar id="testId" class="testClass"></calendar>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- Calendar element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent().trimMargin().trim()),

            /*****************************************DATEPICKER TRANSFORMATIONS ******************************************************/
            mapOf("name" to "DatePicker tag without attributes is removed_1",
                    "givenXml" to """
                                                       <datePicker></datePicker>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!--datePicker element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "DatePicker tag without attributes is removed_2",
                    "givenXml" to """
                                                       <datePicker/>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!--datePicker element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "DatePicker tag with attribute source and size is removed",
                    "givenXml" to """
                                                       <datePicker src="SOME ICAL STRING" size="0"></datePicker>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!--datePicker element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "DatePicker tag with attribute source removed",
                    "givenXml" to """
                                                       <datePicker src="SOME ICAL STRING"></datePicker>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!--datePicker element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "DatePicker tag with attribute size is removed",
                    "givenXml" to """
                                                       <datePicker size="0"></datePicker>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!--datePicker element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent().trimMargin().trim()),

            /*****************************************DATETIMEPICKER TRANSFORMATIONS ******************************************************/
            mapOf("name" to "DateTimePicker tag without attributes is removed_1",
                    "givenXml" to """
                                                       <dateTimePicker></dateTimePicker>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- dateTimePicker element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "DateTimePicker tag without attributes is removed_2",
                    "givenXml" to """
                                                       <dateTimePicker/>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- dateTimePicker element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "DateTimePicker tag with attribute source and size is removed",
                    "givenXml" to """
                                                       <dateTimePicker src="SOME ICAL STRING" size="0"></dateTimePicker>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- dateTimePicker element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent().trimMargin().trim()),
            mapOf("name" to "DateTimePicker tag with attribute source removed",
                    "givenXml" to """
                                                       <dateTimePicker src="SOME ICAL STRING"></dateTimePicker>
                                                 """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- dateTimePicker element was removed-->
                                                        <automaticUpgraded/>
                                                    """.trimIndent().trimMargin().trim()),
            mapOf("name" to "DateTimePicker tag with attribute size is removed",
                    "givenXml" to """
                                                       <dateTimePicker size="0"></dateTimePicker>
                                                   """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- dateTimePicker element was removed-->
                                                        <automaticUpgraded/>
                                                    """.trimIndent().trimMargin().trim()),

            /*****************************************TEXTINPUT TRANSFORMATIONS ******************************************************/

            mapOf("name" to "TextInput tag with attribute id is transformed to a text",
                    "givenXml" to """
                                                       <textInput id="abc"/>
                                                    """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- textInput element was replaced with text-->
                                                        <text id="abc"/>
                                                        <automaticUpgraded/>
                                                    """.trimIndent().trimMargin().trim()),
            mapOf("name" to "TextInput tag with attributes name, placeholder, regex, value, required and class is transformed to a text",
                    "givenXml" to """
                                                       <textInput name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                                    """.trimIndent().trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- textInput element was replaced with text-->
                                                        <text name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                                        <automaticUpgraded/>
                                                    """.trimIndent())
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} given XML: [${it["givenXml"]}] -> ${it["expectedTransformation"]}") {

            transformString(TRANSFORMER_2_0_0, it["givenXml"] as String ,it["expectedTransformation"] as String)
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