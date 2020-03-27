package canon.parser.xml.xlst


import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import java.nio.charset.StandardCharsets
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

internal class CanonXlstTransformerTest {

    private fun transformFromFileSystem(transformer: CanonXlstTransformer, pathToXMLToTransform: String, pathToExpectedXML: String,  expectSuccess: Boolean = true) {
        try {
            val result = transformer.execute(CanonXlstTransformerTest::class.java.getResourceAsStream(pathToXMLToTransform).reader(StandardCharsets.UTF_8).readText())
            println("actualResult $result" )
            val expectedResult = CanonXlstTransformerTest::class.java.getResourceAsStream(pathToExpectedXML).reader(StandardCharsets.UTF_8).readText()
            println("expectedResult $expectedResult" )
            Assertions.assertThat(result).isEqualTo(expectedResult)
        } catch (e: Exception) {
            if (expectSuccess) {
                Assertions.fail<String>("xml '$pathToXMLToTransform' should not throw a transformation exception", e)
            }
        }
    }

    private fun transformString(transformer: CanonXlstTransformer, xmlToTransform: String, expectedXml: String,  expectSuccess: Boolean = true) {
        try {
            val result = transformer.execute(xmlToTransform)
            Assertions.assertThat(result.replace("\\s".toRegex(), "")).isEqualTo(expectedXml.replace("\\s".toRegex(), ""))
        } catch (e: Exception) {
            if (expectSuccess) {
                Assertions.fail<String>("xml '$expectedXml' should not throw a transformation exception", e)
            }
        }
    }


    @Test
    fun complexXmlIsParsed() = transformFromFileSystem(CanonXlstTransformer("/xml/xlst/", SemanticVersion("2.0.0")), "/xml/xlst/complex1.xml","/xml/xlst/expected/complex1.xml")

    @TestFactory
    fun `Transform XML with XSLT`() = listOf(
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

            transformString(CanonXlstTransformer("/xml/xlst/", SemanticVersion("2.0.0")), it["givenXml"] as String ,it["expectedTransformation"] as String)
        }
    }


    @Test
    fun parallelTest() {
        val barrier = CountDownLatch(1)
        val endBarrier = CountDownLatch(25)
        val transformer= CanonXlstTransformer("/xml/xlst/", SemanticVersion("2.0.0"))
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