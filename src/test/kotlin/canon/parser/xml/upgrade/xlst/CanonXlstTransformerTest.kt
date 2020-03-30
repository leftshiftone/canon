package canon.parser.xml.upgrade.xlst


import canon.parser.xml.upgrade.SemanticVersion
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import java.nio.charset.StandardCharsets
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

internal class CanonXlstTransformerTest {

    val WHITE_SPACE_REGEX= "\\s".toRegex()

    private fun transformFromFileSystem(transformer: CanonXlstTransformer, pathToXMLToTransform: String, pathToExpectedXML: String,  expectSuccess: Boolean = true) {
        try {
            val result = transformer.execute(CanonXlstTransformerTest::class.java.getResourceAsStream(pathToXMLToTransform).reader(StandardCharsets.UTF_8).readText())
            val resultInRaw=result.replace(WHITE_SPACE_REGEX, "")
            val expectedResultInRaw = CanonXlstTransformerTest::class.java.getResourceAsStream(pathToExpectedXML).reader(StandardCharsets.UTF_8).readText().replace(WHITE_SPACE_REGEX, "")
            Assertions.assertThat(resultInRaw).isEqualTo(expectedResultInRaw)
        } catch (e: Exception) {
            if (expectSuccess) {
                Assertions.fail<String>("xml '$pathToXMLToTransform' should not throw a transformation exception", e)
            }
        }
    }

    private fun transformString(transformer: CanonXlstTransformer, xmlToTransform: String, expectedXml: String,  expectSuccess: Boolean = true) {
        try {
            val result = transformer.execute(xmlToTransform)
            Assertions.assertThat(result.replace(WHITE_SPACE_REGEX, "")).isEqualTo(expectedXml.replace(WHITE_SPACE_REGEX, ""))
        } catch (e: Exception) {
            if (expectSuccess) {
                Assertions.fail<String>("xml '$expectedXml' should not throw a transformation exception", e)
            }
        }
    }


    @Test
    fun complexXmlIsParsed() = transformFromFileSystem(CanonXlstTransformer("/xml/xlst/transformers", SemanticVersion("2.0.0")), "/xml/xlst/complex1.xml","/xml/xlst/expected/complex1.xml")


    @TestFactory
    fun `Transform XML with XSLT`() = listOf(
            /*****************************************TEXT TRANSFORMATIONS ******************************************************/

            mapOf("name" to "Simple text tag is transformed to a label",
                    "givenXml" to """
                                                <markup>
                                                    <container>
                                                       <text>text</text>
                                                    </container>
                                                 </markup>""".trimMargin().trim(),
                    "expectedTransformation" to """
                                                <markup>
                                                    <container>
                                                        <label>text</label>
                                                    </container>
                                                </markup>""".trimIndent()),
            mapOf("name" to "Text tag with attribute id and value is transformed to a label",
                    "givenXml" to """
                                                <markup>
                                                    <container>
                                                       <text id="abc">text</text>
                                                    </container>
                                                 </markup>""".trimMargin().trim(),
                    "expectedTransformation" to """
                                                <markup>
                                                    <container>
                                                        <label id="abc">text</label>
                                                    </container>
                                                </markup>""".trimIndent()),
            mapOf("name" to "Text tag with attribute id, class and if and value is transformed to a label",
                    "givenXml" to """
                                                <markup>
                                                    <container>
                                                       <text id="abc" class=".cs" if="(aaa)">text</text>
                                                    </container>
                                                 </markup>""".trimMargin().trim(),
                    "expectedTransformation" to """
                                                <markup>
                                                    <container>
                                                        <label id="abc" class=".cs" if="(aaa)">text</label>
                                                    </container>
                                                </markup>""".trimIndent()),
            /*****************************************CALENDAR TRANSFORMATIONS ******************************************************/
            mapOf("name" to "Calendar tag without attributes is removed_1",
                    "givenXml" to """
                                                <markup>
                                                    <container>
                                                       <calendar></calendar>
                                                    </container>
                                                 </markup>""".trimMargin().trim(),
                    "expectedTransformation" to """
                                                <markup>
                                                    <container/>
                                                </markup>""".trimIndent()),
            mapOf("name" to "Calendar tag without attributes is removed_2",
                    "givenXml" to """
                                                <markup>
                                                    <container>
                                                       <calendar/>
                                                    </container>
                                                 </markup>""".trimMargin().trim(),
                    "expectedTransformation" to """
                                                <markup>
                                                    <container/>
                                                </markup>""".trimIndent()),
            mapOf("name" to "Calendar tag with attribute id, class and name is removed",
                    "givenXml" to """
                                                <markup>
                                                    <container>
                                                       <calendar id="testId" class="testClass" name="testName"></calendar>
                                                    </container>
                                                 </markup>""".trimMargin().trim(),
                    "expectedTransformation" to """
                                                <markup>
                                                    <container/>
                                                </markup>""".trimIndent()),
            mapOf("name" to "Calendar tag with attribute id and name removed",
                    "givenXml" to """
                                                <markup>
                                                    <container>
                                                       <calendar id="testId" name="testName"></calendar>
                                                    </container>
                                                 </markup>""".trimMargin().trim(),
                    "expectedTransformation" to """
                                                <markup>
                                                    <container/>
                                                </markup>""".trimIndent()),
            mapOf("name" to "Calendar tag with attribute id and class is removed",
                    "givenXml" to """
                                                <markup>
                                                    <container>
                                                       <calendar id="testId" class="testClass"></calendar>
                                                    </container>
                                                 </markup>""".trimMargin().trim(),
                    "expectedTransformation" to """
                                                <markup>
                                                    <container/>
                                                </markup>""".trimIndent()),

            /*****************************************DATEPICKER TRANSFORMATIONS ******************************************************/
            mapOf("name" to "DatePicker tag without attributes is removed_1",
                    "givenXml" to """
                                                <markup>
                                                    <container>
                                                       <datePicker></datePicker>
                                                    </container>
                                                 </markup>""".trimMargin().trim(),
                    "expectedTransformation" to """
                                                <markup>
                                                    <container/>
                                                </markup>""".trimIndent()),
            mapOf("name" to "DatePicker tag without attributes is removed_2",
                    "givenXml" to """
                                                <markup>
                                                    <container>
                                                       <datePicker/>
                                                    </container>
                                                 </markup>""".trimMargin().trim(),
                    "expectedTransformation" to """
                                                <markup>
                                                    <container/>
                                                </markup>""".trimIndent()),
            mapOf("name" to "DatePicker tag with attribute source and size is removed",
                    "givenXml" to """
                                                <markup>
                                                    <container>
                                                       <datePicker src="SOME ICAL STRING" size="0"></datePicker>
                                                    </container>
                                                 </markup>""".trimMargin().trim(),
                    "expectedTransformation" to """
                                                <markup>
                                                    <container/>
                                                </markup>""".trimIndent()),
            mapOf("name" to "DatePicker tag with attribute source removed",
                    "givenXml" to """
                                                <markup>
                                                    <container>
                                                       <datePicker src="SOME ICAL STRING"></datePicker>
                                                    </container>
                                                 </markup>""".trimMargin().trim(),
                    "expectedTransformation" to """
                                                <markup>
                                                    <container/>
                                                </markup>""".trimIndent()),
            mapOf("name" to "DatePicker tag with attribute size is removed",
                    "givenXml" to """
                                                <markup>
                                                    <container>
                                                       <datePicker size="0"></datePicker>
                                                    </container>
                                                 </markup>""".trimMargin().trim(),
                    "expectedTransformation" to """
                                                <markup>
                                                    <container/>
                                                </markup>""".trimIndent()),

            /*****************************************DATETIMEPICKER TRANSFORMATIONS ******************************************************/
            mapOf("name" to "DateTimePicker tag without attributes is removed_1",
                    "givenXml" to """
                                                <markup>
                                                    <container>
                                                       <dateTimePicker></dateTimePicker>
                                                    </container>
                                                 </markup>""".trimMargin().trim(),
                    "expectedTransformation" to """
                                                <markup>
                                                    <container/>
                                                </markup>""".trimIndent()),
            mapOf("name" to "DateTimePicker tag without attributes is removed_2",
                    "givenXml" to """
                                                <markup>
                                                    <container>
                                                       <dateTimePicker/>
                                                    </container>
                                                 </markup>""".trimMargin().trim(),
                    "expectedTransformation" to """
                                                <markup>
                                                    <container/>
                                                </markup>""".trimIndent()),
            mapOf("name" to "DateTimePicker tag with attribute source and size is removed",
                    "givenXml" to """
                                                <markup>
                                                    <container>
                                                       <dateTimePicker src="SOME ICAL STRING" size="0"></dateTimePicker>
                                                    </container>
                                                 </markup>""".trimMargin().trim(),
                    "expectedTransformation" to """
                                                <markup>
                                                    <container/>
                                                </markup>""".trimIndent()),
            mapOf("name" to "DateTimePicker tag with attribute source removed",
                    "givenXml" to """
                                                <markup>
                                                    <container>
                                                       <dateTimePicker src="SOME ICAL STRING"></dateTimePicker>
                                                    </container>
                                                 </markup>""".trimMargin().trim(),
                    "expectedTransformation" to """
                                                <markup>
                                                    <container/>
                                                </markup>""".trimIndent()),
            mapOf("name" to "DateTimePicker tag with attribute size is removed",
                    "givenXml" to """
                                                <markup>
                                                    <container>
                                                       <dateTimePicker size="0"></dateTimePicker>
                                                    </container>
                                                 </markup>""".trimMargin().trim(),
                    "expectedTransformation" to """
                                                <markup>
                                                    <container/>
                                                </markup>""".trimIndent()),

            /*****************************************TEXTINPUT TRANSFORMATIONS ******************************************************/

            mapOf("name" to "TextInput tag with attribute id is transformed to a text",
                    "givenXml" to """
                                                <markup>
                                                    <container>
                                                       <textInput id="abc"/>
                                                    </container>
                                                 </markup>""".trimMargin().trim(),
                    "expectedTransformation" to """
                                                <markup>
                                                    <container>
                                                        <text id="abc"/>
                                                    </container>
                                                </markup>""".trimIndent()),
            mapOf("name" to "TextInput tag with attributes name, placeholder, regex, value, required and class is transformed to a text",
                    "givenXml" to """
                                                <markup>
                                                    <container>
                                                       <textInput name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                                    </container>
                                                 </markup>""".trimMargin().trim(),
                    "expectedTransformation" to """
                                                <markup>
                                                    <container>
                                                        <textname="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                                    </container>
                                                </markup>""".trimIndent())
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} given XML: [${it["givenXml"]}] -> ${it["expectedTransformation"]}") {

            transformString(CanonXlstTransformer("/xml/xlst/transformers", SemanticVersion("2.0.0")), it["givenXml"] as String ,it["expectedTransformation"] as String)
        }
    }


    @Test
    fun parallelTest() {
        val barrier = CountDownLatch(1)
        val endBarrier = CountDownLatch(25)
        val transformer= CanonXlstTransformer("/xml/xlst/transformers", SemanticVersion("2.0.0"))
        (0..24).forEach {
            Thread {
                try{
                    barrier.await(10, TimeUnit.SECONDS)
                    println("Thread-${it} starting execution")
                    transformFromFileSystem(transformer,"/xml/xlst/complex1.xml","/xml/xlst/expected/complex1.xml")
                } finally {
                    endBarrier.countDown()
                }
            }.start()
        }
        barrier.countDown()
        endBarrier.await(60, TimeUnit.SECONDS)
    }
}