package canon.parser.xml.upgrade.xslt

import canon.parser.xml.upgrade.SemanticVersion
import canon.parser.xml.upgrade.xslt.XSLTTransformSupport.Companion.getDefaultTransformers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

internal class XSLTUpgradeHandlerTest {
    private val testTransformerDir = "/xml/xslt/testtransformers/"

    val DEFAULT_TRANSFORMERS = getDefaultTransformers()
    val TEST_TRANSFORMERS = getDefaultTransformers().plus(XSLTTransformSupport.getResourceFiles(testTransformerDir).map { testTransformerDir.plus(it) })


    @Test
    fun `A null version requires an upgrade`() {
        val classUnderTest = XSLTUpgradeHandler(DEFAULT_TRANSFORMERS)
        assertThat(classUnderTest.isUpgradeRequired(null)).isTrue()
    }

    @Test
    fun `No version specified in isUpgradeRequired method is true`() {
        val classUnderTest = XSLTUpgradeHandler(DEFAULT_TRANSFORMERS)
        assertThat(classUnderTest.isUpgradeRequired()).isTrue()
    }

    @TestFactory
    @Disabled("This test is temp ignored until version 2.0.0 is released")
    fun `given a the current version, a transformation is not needed`() = listOf(
            mapOf("name" to "specifiedTransformerPath", "transformerPath" to "/xml/xslt/transformers")
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} transformer Path: [${it["transformerPath"]}]") {
            val classUnderTest = XSLTUpgradeHandler(DEFAULT_TRANSFORMERS)
            assertThat(classUnderTest.isUpgradeRequired(classUnderTest.getLatestVersion())).isFalse()
        }
    }


    @Test
    fun `given a folder with one xslt newer than the current version, the transformerIterator has one transformation`() {
        val classUnderTest = XSLTUpgradeHandler(DEFAULT_TRANSFORMERS)
        assertThat(classUnderTest.transformersForVersion("1.9.0")).isNotNull()

    }

    @Test
    fun  `given a folder with 4 newer xslt files than the current version, the transformerIterator contains all of them and in ASC order`() {
            val classUnderTest = XSLTUpgradeHandler(TEST_TRANSFORMERS)
            val transformerList = classUnderTest.transformersForVersion("1.0.0")
            assertThat(transformerList).isNotNull()
            assertThat(transformerList).size().isEqualTo(4)
            assertThat(transformerList).extracting("config").extracting("version")
                    .contains(
                             SemanticVersion("1.3.0")
                            ,SemanticVersion("1.5.0")
                            ,SemanticVersion("2.0.0")
                            ,SemanticVersion("2.2.0")
                    )
    }

    @Test
    fun  `given a folder with one older xslt files and one newer than the current version, the transformerIterator contains just the newer and in ASC order`() {
            val classUnderTest = XSLTUpgradeHandler(DEFAULT_TRANSFORMERS)
            val transformerList = classUnderTest.transformersForVersion("2.0.0")
            assertThat(transformerList).isNotNull()
            assertThat(transformerList).size().isEqualTo(1)
            assertThat(transformerList).element(0).extracting { it.config.version }.isEqualTo(SemanticVersion("2.2.0"))
    }

    @TestFactory
    fun `Transform a XML with multiple sequential transformers`() = listOf(
            mapOf(
                    "name" to "Transform XML through transformers: 1.0.0 , 1.3.0, 1.5.0 and 2.0.0 but no changes must be made",
                    "version" to "0.9.0",
                    "rawXml" to """


                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                    </block>
                                </carousel>


                                """.trimMargin().trim(),
                    "expectedTransformedXml" to """


                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                    </block>
                                </carousel>


                                """.trimMargin().trim()
            ),
            mapOf(
                    "name" to " (FALLBACK CASE) Transform XML through transformers: 2.0.0 because no version is given",
                    "version" to null,
                    "rawXml" to """


                                <text id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</text>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                        <text>address</text>
                                        <textInput name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                        <textA>A</textA>
                                        <textB>B</textB>
                                        <textC>C</textC>
                                    </block>
                                </carousel>


                                """.trimMargin().trim(),
                    "expectedTransformedXml" to """


                                <label id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</label>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                        <label>address</label>
                                        <text name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                        <textA>A</textA>
                                        <textB>B</textB>
                                        <textC>C</textC>
                                    </block>
                                </carousel>



                                """.trimMargin().trim()
            ),
            mapOf(
                    "name" to "Transform XML through transformers: 1.0.0 , 1.3.0, 1.5.0 and 2.0.0",
                    "version" to "0.9.0",
                    "rawXml" to """


                                <text id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</text>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                        <text>address</text>
                                        <textInput name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                        <textA>A</textA>
                                        <textB>B</textB>
                                        <textC>C</textC>
                                    </block>
                                </carousel>


                                """.trimMargin().trim(),
                    "expectedTransformedXml" to """


                                <label id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</label>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                        <label>address</label>
                                        <text name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                        <labelA>A</labelA>
                                        <labelB>B</labelB>
                                        <labelC>C</labelC>
                                    </block>
                                </carousel>


                                """.trimMargin().trim()
            )
            ,
            mapOf(
                    "name" to "Transform XML through transformers: 1.5.0 and 2.0.0",
                    "version" to "1.3.1",
                    "rawXml" to """


                                <text id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</text>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                        <text>address</text>
                                        <textInput name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                        <textA>A</textA>
                                        <textB>B</textB>
                                        <textC>C</textC>
                                    </block>
                                </carousel>


                                """.trimMargin().trim(),
                    "expectedTransformedXml" to """


                                <label id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</label>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                        <label>address</label>
                                        <text name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                        <textA>A</textA>
                                        <textB>B</textB>
                                        <labelC>C</labelC>
                                    </block>
                                </carousel>


                                """.trimMargin().trim()
            ),
            mapOf(
                    "name" to "Transform XML through transformers: 2.0.0",
                    "version" to "1.9.0",
                    "rawXml" to """


                                <text id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</text>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                        <text>address</text>
                                        <textInput name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                        <textA>A</textA>
                                        <textB>B</textB>
                                        <textC>C</textC>
                                    </block>
                                </carousel>


                                """.trimMargin().trim(),
                    "expectedTransformedXml" to """


                                <label id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</label>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                        <label>address</label>
                                        <text name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                        <textA>A</textA>
                                        <textB>B</textB>
                                        <textC>C</textC>
                                    </block>
                                </carousel>


                                """.trimMargin().trim()
            ),
            mapOf(
                    "name" to " No transform is required",
                    "version" to "2.0.0",
                    "rawXml" to """


                                <text id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</text>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                        <text>address</text>
                                        <textInput name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                        <textA>A</textA>
                                        <textB>B</textB>
                                        <textC>C</textC>
                                    </block>
                                </carousel>


                                """.trimMargin().trim(),
                    "expectedTransformedXml" to """


                                <text id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</text>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                        <text>address</text>
                                        <textInput name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                        <textA>A</textA>
                                        <textB>B</textB>
                                        <textC>C</textC>
                                    </block>
                                </carousel>


                                """.trimMargin().trim()
            ),
            mapOf(
                    "name" to "Transform XML through transformers: 2.0.0, 2.2.0",
                    "version" to "1.9.0",
                    "rawXml" to """


                                <selection countdownInSec="0" name="psychotest">
                                    <block name="p1" class="p1">
                                        <block name="p11">
                                            <headline>Bei der Arbeit bin ich:</headline>
                                        </block>
                                        <block name="p12" class="psycho-row">
                                            <block name="p13" class="psycho-col left">
                                                <label>Zielstrebig</label>
                                            </block>
                                            <block name="p14" class="psycho-col">
                                                <label>Kooperativ</label>
                                            </block>
                                        </block>
                                    </block>
                                </selection>


                                """.trimMargin().trim(),
                    "expectedTransformedXml" to """


                                <selection countdownInSec="0" name="psychotest">
                                    <selectionItem name="p1" class="p1">
                                        <block name="p11">
                                            <headline>Bei der Arbeit bin ich:</headline>
                                        </block>
                                        <block name="p12" class="psycho-row">
                                            <selectable name="p13" class="psycho-col left">
                                                <label>Zielstrebig</label>
                                            </selectable>
                                            <selectable name="p14" class="psycho-col">
                                                <label>Kooperativ</label>
                                            </selectable>
                                        </block>
                                    </selectionItem>
                                </selection>


                                """.trimMargin().trim()
            ),
            mapOf(
                    "name" to "Transform XML through transformers: 2.2.0",
                    "version" to "2.0.0",
                    "rawXml" to """


                                <selection countdownInSec="0" name="psychotest">
                                    <block name="p1" class="p1">
                                        <block name="p11">
                                            <headline>Bei der Arbeit bin ich:</headline>
                                        </block>
                                        <block name="p12" class="psycho-row">
                                            <block name="p13" class="psycho-col left">
                                                <label>Zielstrebig</label>
                                            </block>
                                            <block name="p14" class="psycho-col">
                                                <label>Kooperativ</label>
                                            </block>
                                        </block>
                                    </block>
                                </selection>


                                """.trimMargin().trim(),
                    "expectedTransformedXml" to """


                                <selection countdownInSec="0" name="psychotest">
                                    <selectionItem name="p1" class="p1">
                                        <block name="p11">
                                            <headline>Bei der Arbeit bin ich:</headline>
                                        </block>
                                        <block name="p12" class="psycho-row">
                                            <selectable name="p13" class="psycho-col left">
                                                <label>Zielstrebig</label>
                                            </selectable>
                                            <selectable name="p14" class="psycho-col">
                                                <label>Kooperativ</label>
                                            </selectable>
                                        </block>
                                    </selectionItem>
                                </selection>


                                """.trimMargin().trim()
            ),
            mapOf(
                    "name" to "null",
                    "version" to "2.0.0",
                    "rawXml" to null,
                    "expectedTransformedXml" to ""
            )
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} given XML: [${it["rawXml"]}] -> ${it["expectedTransformedXml"]}") {
            val classUnderTest = XSLTUpgradeHandler(TEST_TRANSFORMERS)
            val transformedXml = classUnderTest.upgrade(it["rawXml"] as String?, it["version"])
            assertThat(transformedXml.replace("\\s".toRegex(), "")).isEqualTo(it["expectedTransformedXml"]!!.replace("\\s".toRegex(), ""))
        }
    }


    @TestFactory
    fun `Transform a list of utterances with multiple sequential transformers`() = listOf(
            mapOf(
                    "name" to "YYY",
                    "version" to "0.9.0",
                    "utterance" to mapOf("de" to listOf("""

                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                    </block>
                                </carousel>

                                """.trimMargin().trim(),
                            """

                                <text id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</text>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                        <text>address</text>
                                        <textInput name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                        <textA>A</textA>
                                        <textB>B</textB>
                                        <textC>C</textC>
                                    </block>
                                </carousel>

                                """.trimMargin().trim(),
                            """

                                <text id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</text>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                        <text>address</text>
                                        <textInput name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                        <textA>A</textA>
                                        <textC>C</textC>
                                    </block>
                                </carousel>

                                """.trimMargin().trim())),


                    "expectedUtterance" to mapOf("de" to listOf("""


                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                    </block>
                                </carousel>


                                """.trimMargin().trim(),
                            """


                                <label id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</label>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                        <label>address</label>
                                        <text name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                        <labelA>A</labelA>
                                        <labelB>B</labelB>
                                        <labelC>C</labelC>
                                    </block>
                                </carousel>


                                """.trimMargin().trim(),
                            """


                                <label id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</label>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                        <label>address</label>
                                        <text name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                        <labelA>A</labelA>
                                        <labelC>C</labelC>
                                    </block>
                                </carousel>


                                """.trimMargin().trim())
                    )
            )
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} given XML: [${it["utterance"]}]") {
            val classUnderTest = XSLTUpgradeHandler(TEST_TRANSFORMERS)
            val transformedXml = classUnderTest.upgrade(it["utterance"] as Map<String, List<String>>, it["version"] as String)
            transformedXml.get("de")!!.forEachIndexed { i, utterance ->
                assertThat(utterance.replace("\\s".toRegex(), "")).isEqualTo((it["expectedUtterance"] as Map<String, List<String>>).get("de")!!.get(i).replace("\\s".toRegex(), ""))
            }
        }
    }


    @TestFactory
    fun `Transform a list of utterances in multiple languages through multiple sequential transformers`() = listOf(
            mapOf(
                    "name" to "YYY",
                    "version" to "0.9.0",
                    "utterance" to
                            mapOf("de" to listOf("""

                                        <carousel>
                                            <block foreach="">
                                                <headline>name</headline>
                                            </block>
                                        </carousel>

                                        """.trimMargin().trim(),
                                    """

                                        <text id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</text>
                                        <carousel>
                                            <block foreach="">
                                                <headline>name</headline>
                                                <text>address</text>
                                                <textInput name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                                <textA>A</textA>
                                                <textB>B</textB>
                                                <textC>C</textC>
                                            </block>
                                        </carousel>

                                        """.trimMargin().trim()),

                                    "en" to listOf("""

                                        <carousel>
                                            <block foreach="">
                                                <headline>name in English</headline>
                                            </block>
                                        </carousel>

                                        """.trimMargin().trim(),
                                            """

                                        <text id="123">Based on the Input we can suggest following results:</text>
                                        <carousel>
                                            <block foreach="">
                                                <headline>name</headline>
                                                <text>address</text>
                                                <textInput name="textInputInEnglish" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                                <textA>A</textA>
                                                <textB>B</textB>
                                                <textC>C</textC>
                                            </block>
                                        </carousel>

                                        """.trimMargin().trim())),

                    "expectedUtterance" to mapOf("de" to listOf("""


                                        <carousel>
                                            <block foreach="">
                                                <headline>name</headline>
                                            </block>
                                        </carousel>


                                        """.trimMargin().trim(),
                            """


                                        <label id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</label>
                                        <carousel>
                                            <block foreach="">
                                                <headline>name</headline>
                                                <label>address</label>
                                                <text name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                                <labelA>A</labelA>
                                                <labelB>B</labelB>
                                                <labelC>C</labelC>
                                            </block>
                                        </carousel>


                                        """.trimMargin().trim()),

                            "en" to listOf("""


                                                <carousel>
                                                    <block foreach="">
                                                        <headline>name in English</headline>
                                                    </block>
                                                </carousel>


                                                """.trimMargin().trim(),
                                    """


                                                <label id="123">Based on the Input we can suggest following results:</label>
                                                <carousel>
                                                    <block foreach="">
                                                        <headline>name</headline>
                                                         <label>address</label>
                                                        <text name="textInputInEnglish" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                                        <labelA>A</labelA>
                                                        <labelB>B</labelB>
                                                        <labelC>C</labelC>
                                                    </block>
                                                </carousel>


                                                """.trimMargin().trim())
                    )
            ),

            mapOf(
                    "name" to "null",
                    "version" to "0.9.0",
                    "utterance" to null,
                    "expectedUtterance" to mapOf<String, List<String>>()
            )
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} given XML: [${it["utterance"]}]") {
            val classUnderTest = XSLTUpgradeHandler(TEST_TRANSFORMERS)
            val transformedXml = classUnderTest.upgrade(it["utterance"] as Map<String, List<String>>?, it["version"] as String)

            transformedXml.entries.forEach { utteranceEntry ->
                utteranceEntry.value.forEachIndexed { index, utterance ->
                    assertThat(utterance.replace("\\s".toRegex(), "")).isEqualTo((it["expectedUtterance"] as Map<String, List<String>>).get(utteranceEntry.key)!!.get(index).replace("\\s".toRegex(), ""))
                }
            }
        }
    }

    /**
     * INFO: This test will fail if instead of the default [TransformerFactory] the xalan one is used (which is a testRuntime dependency)
     * see: build.gradle
     */
    @Test
    fun `is not empty eventhough xalan is on the classpath`() {
        val classUnderTest = XSLTUpgradeHandler()
        val map = mapOf("de" to listOf("<text>hallo welt \uD83D\uDC49</text>"))
        val result = classUnderTest.upgrade(map, null)

        assertThat(result["de"]?.first()).isNotBlank()
    }
}
