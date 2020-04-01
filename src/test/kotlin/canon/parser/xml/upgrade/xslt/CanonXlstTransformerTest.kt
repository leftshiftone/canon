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
            val result = transformer.execute(XSLTTransformerTest::class.java.getResourceAsStream(pathToXMLToTransform).reader(StandardCharsets.UTF_8).readText())
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
            Assertions.assertThat(result.replace(WHITE_SPACE_REGEX, "")).isEqualTo(expectedXml.replace(WHITE_SPACE_REGEX, ""))
        } catch (e: Exception) {
            if (expectSuccess) {
                Assertions.fail<String>("xml '$expectedXml' should not throw a transformation exception", e)
            }
        }
    }


    @Test
    fun complexXmlIsParsed() = transformFromFileSystem(TRANSFORMER_2_0_0, "/xml/xslt/complex1.xml","/xml/xslt/expected/complex1.xml")


    @TestFactory
    fun `Transform XML with XSLT`() = listOf(
            /*****************************************TEXT TRANSFORMATIONS ******************************************************/

            mapOf("name" to "Simple text tag is transformed to a label",
                    "givenXml" to """
                                                       <text>text</text>
                                                 """.trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- text element was replaced with label-->
                                                        <label>text</label>
                                                        <automaticUpgraded/>
                                                """.trimIndent()),
            mapOf("name" to "Text tag with attribute id and value is transformed to a label",
                    "givenXml" to """
                                                       <text id="abc">text</text>
                                                 """.trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- text element was replaced with label-->
                                                        <label id="abc">text</label>
                                                        <automaticUpgraded/>
                                                """.trimIndent()),
            mapOf("name" to "Text tag with attribute id, class and if and value is transformed to a label",
                    "givenXml" to """
                                                       <text id="abc" class=".cs" if="(aaa)">text</text>
                                                 """.trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- text element was replaced with label-->
                                                        <label id="abc" class=".cs" if="(aaa)">text</label>
                                                        <automaticUpgraded/>
                                                """.trimIndent()),
            mapOf("name" to "Text tag with attribute id, class, if , name and value is transformed to a label",
                    "givenXml" to """
                                                       <text id="abc" name="textname" class=".cs" if="(aaa)">text</text>
                                                 """.trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- text element was replaced with label-->
                                                        <!-- attribute name of element text was removed when converting it to label-->
                                                        <label id="abc" class=".cs" if="(aaa)">text</label>
                                                        <automaticUpgraded/>
                                                """.trimIndent()),
            /*****************************************CALENDAR TRANSFORMATIONS ******************************************************/
            mapOf("name" to "Calendar tag without attributes is removed_1",
                    "givenXml" to """
                                                       <calendar></calendar>
                                                 """.trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- Calendar element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent()),
            mapOf("name" to "Calendar tag without attributes is removed_2",
                    "givenXml" to """
                                                       <calendar/>
                                                 """.trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- Calendar element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent()),
            mapOf("name" to "Calendar tag with attribute id, class and name is removed",
                    "givenXml" to """
                                                       <calendar id="testId" class="testClass" name="testName"></calendar>
                                                 """.trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- Calendar element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent()),
            mapOf("name" to "Calendar tag with attribute id and name removed",
                    "givenXml" to """
                                                       <calendar id="testId" name="testName"></calendar>
                                                 """.trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- Calendar element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent()),
            mapOf("name" to "Calendar tag with attribute id and class is removed",
                    "givenXml" to """
                                                       <calendar id="testId" class="testClass"></calendar>
                                                 """.trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- Calendar element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent()),

            /*****************************************DATEPICKER TRANSFORMATIONS ******************************************************/
            mapOf("name" to "DatePicker tag without attributes is removed_1",
                    "givenXml" to """
                                                       <datePicker></datePicker>
                                                 """.trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- datePicker element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent()),
            mapOf("name" to "DatePicker tag without attributes is removed_2",
                    "givenXml" to """
                                                       <datePicker/>
                                                 """.trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- datePicker element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent()),
            mapOf("name" to "DatePicker tag with attribute source and size is removed",
                    "givenXml" to """
                                                       <datePicker src="SOME ICAL STRING" size="0"></datePicker>
                                                 """.trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- datePicker element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent()),
            mapOf("name" to "DatePicker tag with attribute source removed",
                    "givenXml" to """
                                                       <datePicker src="SOME ICAL STRING"></datePicker>
                                                 """.trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- datePicker element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent()),
            mapOf("name" to "DatePicker tag with attribute size is removed",
                    "givenXml" to """
                                                       <datePicker size="0"></datePicker>
                                                 """.trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- datePicker element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent()),

            /*****************************************DATETIMEPICKER TRANSFORMATIONS ******************************************************/
            mapOf("name" to "DateTimePicker tag without attributes is removed_1",
                    "givenXml" to """
                                                       <dateTimePicker></dateTimePicker>
                                                 """.trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- dateTimePicker element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent()),
            mapOf("name" to "DateTimePicker tag without attributes is removed_2",
                    "givenXml" to """
                                                       <dateTimePicker/>
                                                 """.trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- dateTimePicker element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent()),
            mapOf("name" to "DateTimePicker tag with attribute source and size is removed",
                    "givenXml" to """
                                                       <dateTimePicker src="SOME ICAL STRING" size="0"></dateTimePicker>
                                                 """.trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- dateTimePicker element was removed-->
                                                        <automaticUpgraded/>
                                                """.trimIndent()),
            mapOf("name" to "DateTimePicker tag with attribute source removed",
                    "givenXml" to """
                                                       <dateTimePicker src="SOME ICAL STRING"></dateTimePicker>
                                                 """.trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- dateTimePicker element was removed-->
                                                        <automaticUpgraded/>
                                                    """.trimIndent()),
            mapOf("name" to "DateTimePicker tag with attribute size is removed",
                    "givenXml" to """
                                                       <dateTimePicker size="0"></dateTimePicker>
                                                   """.trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- dateTimePicker element was removed-->
                                                        <automaticUpgraded/>
                                                    """.trimIndent()),

            /*****************************************TEXTINPUT TRANSFORMATIONS ******************************************************/

            mapOf("name" to "TextInput tag with attribute id is transformed to a text",
                    "givenXml" to """
                                                       <textInput id="abc"/>
                                                    """.trimMargin().trim(),
                    "expectedTransformation" to """
                                                        <!-- textInput element was replaced with text-->
                                                        <text id="abc"/>
                                                        <automaticUpgraded/>
                                                    """.trimIndent()),
            mapOf("name" to "TextInput tag with attributes name, placeholder, regex, value, required and class is transformed to a text",
                    "givenXml" to """
                                                       <textInput name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                                    """.trimMargin().trim(),
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