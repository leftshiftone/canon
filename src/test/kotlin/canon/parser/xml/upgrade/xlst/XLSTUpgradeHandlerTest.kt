package canon.parser.xml.upgrade.xlst

import canon.parser.xml.upgrade.SemanticVersion
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

internal class XLSTUpgradeHandlerTest {


    @Test
    fun  `given a folder with no XLST files, no transformation is done`(){
        val classUnderTest = XLSTUpgradeHandler("/xml/xlst/XXX/")
        assertThat(classUnderTest.buildTransformerIterator("1.2.0")).isNull()
    }

    @TestFactory
    @Disabled("This test is temp ignored until version 2.0.0 is released")
    fun  `given a the current version, a transformation is not needed`() = listOf(
            mapOf("name" to "defaultTransformerPath", "transformerPath" to null),mapOf("name" to "specifiedTransformerPath","transformerPath" to "/xml/xlst/transformers")
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} transformer Path: [${it["transformerPath"]}]") {
            val classUnderTest = if (it["transformerPath"] == null) XLSTUpgradeHandler() else XLSTUpgradeHandler(it["transformerPath"] as String)
            assertThat(classUnderTest.isUpgradeRequired(classUnderTest.getLatestVersion())).isFalse()
        }
    }


    @TestFactory
    fun  `given a folder with one XLST older than the current version, no transformation is done`() = listOf(
            mapOf("name" to "defaultTransformerPath", "transformerPath" to null),mapOf("name" to "specifiedTransformerPath","transformerPath" to "/xml/xlst/transformers")
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} transformer Path: [${it["transformerPath"]}]") {
            val classUnderTest = if (it["transformerPath"] == null) XLSTUpgradeHandler() else XLSTUpgradeHandler(it["transformerPath"] as String)
            assertThat(classUnderTest.buildTransformerIterator("3.2.0")).isNull()
        }
    }

    @TestFactory
    fun  `given a folder with one XLST newer than the current version, the transformerIterator has one transformation`() = listOf(
            mapOf("name" to "defaultTransformerPath", "transformerPath" to null),mapOf("name" to "specifiedTransformerPath","transformerPath" to "/xml/xlst/transformers")
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} transformer Path: [${it["transformerPath"]}]") {
            val classUnderTest = if (it["transformerPath"] == null) XLSTUpgradeHandler() else XLSTUpgradeHandler(it["transformerPath"] as String)
            assertThat(classUnderTest.buildTransformerIterator("1.9.0")).isNotNull()
        }
    }

    @TestFactory
    fun  `given a folder with 4 newer XLST files than the current version, the transformerIterator contains all of them and in ASC order`() = listOf(
            mapOf("name" to "defaultTransformerPath", "transformerPath" to null),mapOf("name" to "specifiedTransformerPath","transformerPath" to "/xml/xlst/transformers")
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} transformer Path: [${it["transformerPath"]}]") {
            val classUnderTest = if (it["transformerPath"] == null) XLSTUpgradeHandler() else XLSTUpgradeHandler(it["transformerPath"] as String)
            val iterator = classUnderTest.buildTransformerIterator("0.9.0")
            assertThat(iterator).isNotNull()
            assertThat(iterator!!.next().version).isEqualTo(SemanticVersion("1.0.0"))
            assertThat(iterator.next().version).isEqualTo(SemanticVersion("1.3.0"))
            assertThat(iterator.next().version).isEqualTo(SemanticVersion("1.5.0"))
            assertThat(iterator.next().version).isEqualTo(SemanticVersion("2.0.0"))
            assertThat(iterator.hasNext()).isFalse()
        }
    }

    @TestFactory
    fun  `given a folder with one older XLST files and one newer than the current version, the transformerIterator contains just the newer and in ASC order`() = listOf(
            mapOf("name" to "defaultTransformerPath", "transformerPath" to null),mapOf("name" to "specifiedTransformerPath","transformerPath" to "/xml/xlst/transformers")
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} transformer Path: [${it["transformerPath"]}]") {
            val classUnderTest = if (it["transformerPath"] == null) XLSTUpgradeHandler() else XLSTUpgradeHandler(it["transformerPath"] as String)
            val iterator = classUnderTest.buildTransformerIterator("1.6.0")
            assertThat(iterator).isNotNull()
            assertThat(iterator!!.next().version).isEqualTo(SemanticVersion("2.0.0"))
            assertThat(iterator.hasNext()).isFalse()
        }
    }

    @TestFactory
    fun `Transform a XML with multiple sequential transformers`() = listOf(
            mapOf(
                "name" to "Transform XML through transformers: 1.0.0 , 1.3.0, 1.5.0 and 2.0.0 but no changes must be made",
                "version" to "0.9.0",
                "rawXml" to """
                        <markup>
                            <container>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                    </block>
                                </carousel>
                            </container>
                        </markup>
                                """.trimMargin().trim(),
                "expectedTransformedXml" to """
                        <markup>
                            <container>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                    </block>
                                </carousel>
                            </container>
                        </markup>
                                """.trimMargin().trim()
            ),
            mapOf(
                    "name" to " (FALLBACK CASE) Transform XML through transformers: 2.0.0 because no version is given",
                    "version" to null,
                    "rawXml" to """
                        <markup>
                            <container>
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
                            </container>
                        </markup>
                                """.trimMargin().trim(),
                    "expectedTransformedXml" to """
                        <markup>
                            <container>
                                <!-- text element was replaced for label by the XSLT Transformer-->
                                <label id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</label>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                         <!-- text element was replaced for label by the XSLT Transformer-->
                                        <label>address</label>
                                        <!-- textInput element was replaced for text by the XSLT Transformer-->
                                        <text name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                        <textA>A</textA>
                                        <textB>B</textB>
                                        <textC>C</textC>
                                    </block>
                                </carousel>
                            <comment>Document was automatically migrated to version CANON-2.0.0 by a XLST Trasnformer: transform_2.0.0.xlst</comment>
                            </container>
                        </markup>
                                """.trimMargin().trim()
            ),
            mapOf(
                "name" to "Transform XML through transformers: 1.0.0 , 1.3.0, 1.5.0 and 2.0.0",
                "version" to "0.9.0",
                "rawXml" to """
                        <markup>
                            <container>
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
                            </container>
                        </markup>
                                """.trimMargin().trim(),
                "expectedTransformedXml" to """
                        <markup>
                            <container>
                                 <!-- text element was replaced for label by the XSLT Transformer-->
                                <label id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</label>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                         <!-- text element was replaced for label by the XSLT Transformer-->
                                        <label>address</label>
                                        <!-- textInput element was replaced for text by the XSLT Transformer-->
                                        <text name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                        <labelA>A</labelA>
                                        <labelB>B</labelB>
                                        <labelC>C</labelC>
                                    </block>
                                </carousel>
                            <comment>Document was automatically migrated to version CANON-2.0.0 by a XLST Trasnformer: transform_2.0.0.xlst</comment>
                            </container>
                        </markup>
                                """.trimMargin().trim()
            )
            ,
            mapOf(
                    "name" to "Transform XML through transformers: 1.5.0 and 2.0.0",
                    "version" to "1.3.1",
                    "rawXml" to """
                        <markup>
                            <container>
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
                            </container>
                        </markup>
                                """.trimMargin().trim(),
                    "expectedTransformedXml" to """
                        <markup>
                            <container>
                                 <!-- text element was replaced for label by the XSLT Transformer-->
                                <label id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</label>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                         <!-- text element was replaced for label by the XSLT Transformer-->
                                        <label>address</label>
                                        <!-- textInput element was replaced for text by the XSLT Transformer-->
                                        <text name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                        <textA>A</textA>
                                        <textB>B</textB>
                                        <labelC>C</labelC>
                                    </block>
                                </carousel>
                            <comment>Document was automatically migrated to version CANON-2.0.0 by a XLST Trasnformer: transform_2.0.0.xlst</comment>
                            </container>
                        </markup>
                                """.trimMargin().trim()
            ),
            mapOf(
                    "name" to "Transform XML through transformers: 2.0.0",
                    "version" to "1.9.0",
                    "rawXml" to """
                        <markup>
                            <container>
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
                            </container>
                        </markup>
                                """.trimMargin().trim(),
                    "expectedTransformedXml" to """
                        <markup>
                            <container>
                                 <!-- text element was replaced for label by the XSLT Transformer-->
                                <label id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</label>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                         <!-- text element was replaced for label by the XSLT Transformer-->
                                        <label>address</label>
                                        <!-- textInput element was replaced for text by the XSLT Transformer-->
                                        <text name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                        <textA>A</textA>
                                        <textB>B</textB>
                                        <textC>C</textC>
                                    </block>
                                </carousel>
                            <comment>Document was automatically migrated to version CANON-2.0.0 by a XLST Trasnformer: transform_2.0.0.xlst</comment>
                            </container>
                        </markup>
                                """.trimMargin().trim()
            ),
            mapOf(
                    "name" to " No transform is required",
                    "version" to "2.0.0",
                    "rawXml" to """
                        <markup>
                            <container>
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
                            </container>
                        </markup>
                                """.trimMargin().trim(),
                    "expectedTransformedXml" to """
                        <markup>
                            <container>
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
                            </container>
                        </markup>
                                """.trimMargin().trim()
            )
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} given XML: [${it["rawXml"]}] -> ${it["expectedTransformedXml"]}") {
            val classUnderTest = XLSTUpgradeHandler()
            val transformedXml = if(it["version"]==null) classUnderTest.upgrade(it["rawXml"] as String) else classUnderTest.upgrade(it["rawXml"] as String, it["version"] as String)
            assertThat(transformedXml.replace("\\s".toRegex(), "")).isEqualTo(it["expectedTransformedXml"]!!.replace("\\s".toRegex(), ""))

        }
    }




    @TestFactory
    fun `Transform a list of utterances with multiple sequential transformers`() = listOf(
            mapOf(
                    "name" to "YYY",
                    "version" to "0.9.0",
                    "utterance" to mapOf("de" to listOf("""
                        <markup>
                            <container>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                    </block>
                                </carousel>
                            </container>
                        </markup>
                                """.trimMargin().trim(),
                     """
                        <markup>
                            <container>
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
                           </container>
                        </markup>
                                """.trimMargin().trim(),
                    """
                        <markup>
                            <container>
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
                           </container>
                        </markup>
                                """.trimMargin().trim())),


                    "expectedUtterance" to mapOf("de" to listOf("""
                        <markup>
                            <container>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                    </block>
                                </carousel>
                           </container>
                        </markup>
                                """.trimMargin().trim(),
                            """
                        <markup>
                            <container>
                               <!-- text element was replaced for label by the XSLT Transformer-->
                                <label id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</label>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                        <!-- text element was replaced for label by the XSLT Transformer-->
                                        <label>address</label>
                                        <!-- textInput element was replaced for text by the XSLT Transformer-->
                                        <text name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                        <labelA>A</labelA>
                                        <labelB>B</labelB>
                                        <labelC>C</labelC>
                                    </block>
                                </carousel>
                              <comment>Document was automatically migrated to version CANON-2.0.0 by a XLST Trasnformer: transform_2.0.0.xlst</comment>
                           </container>
                        </markup>
                                """.trimMargin().trim(),
                            """
                        <markup>
                            <container>
                                <!-- text element was replaced for label by the XSLT Transformer-->
                                <label id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</label>
                                <carousel>
                                    <block foreach="">
                                        <headline>name</headline>
                                        <!-- text element was replaced for label by the XSLT Transformer-->
                                        <label>address</label>
                                        <!-- textInput element was replaced for text by the XSLT Transformer-->
                                        <text name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                        <labelA>A</labelA>
                                        <labelC>C</labelC>
                                    </block>
                                </carousel>
                             <comment>Document was automatically migrated to version CANON-2.0.0 by a XLST Trasnformer: transform_2.0.0.xlst</comment>
                            </container>
                        </markup>
                                """.trimMargin().trim())
                    )
            )
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} given XML: [${it["utterance"]}]") {
            val classUnderTest = XLSTUpgradeHandler("/xml/xlst/transformers")
            val transformedXml = classUnderTest.upgrade(it["utterance"] as Map<String,List<String>>, it["version"] as String)
            transformedXml.get("de")!!.forEachIndexed {i, utterance ->
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
                                <markup>
                                    <container>
                                        <carousel>
                                            <block foreach="">
                                                <headline>name</headline>
                                            </block>
                                        </carousel>
                                    </container>
                                </markup>
                                        """.trimMargin().trim(),
                                    """
                                <markup>
                                    <container>
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
                                    </container>
                                </markup>
                                        """.trimMargin().trim()),

                            "en" to listOf("""
                                <markup>
                                    <container>
                                        <carousel>
                                            <block foreach="">
                                                <headline>name in English</headline>
                                            </block>
                                        </carousel>
                                    </container>
                                </markup>
                                        """.trimMargin().trim(),
                                    """
                                <markup>
                                    <container>
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
                                    </container>
                                </markup>
                                        """.trimMargin().trim())),

                            "expectedUtterance" to mapOf("de" to listOf("""
                                <markup>
                                    <container>
                                        <carousel>
                                            <block foreach="">
                                                <headline>name</headline>
                                            </block>
                                        </carousel>
                                     </container>
                                </markup>
                                        """.trimMargin().trim(),
                                    """
                                <markup>
                                    <container>
                                        <!-- text element was replaced for label by the XSLT Transformer-->
                                        <label id="123">Basierend auf Ihren Angaben können wir Ihnen folgende  Resultate vorschlagen:</label>
                                        <carousel>
                                            <block foreach="">
                                                <headline>name</headline>
                                                <!-- text element was replaced for label by the XSLT Transformer-->
                                                <label>address</label>
                                                <!-- textInput element was replaced for text by the XSLT Transformer-->
                                                <text name="textInput" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                                <labelA>A</labelA>
                                                <labelB>B</labelB>
                                                <labelC>C</labelC>
                                            </block>
                                        </carousel>
                                    <comment>Document was automatically migrated to version CANON-2.0.0 by a XLST Trasnformer: transform_2.0.0.xlst</comment>
                                    </container>
                                </markup>
                                        """.trimMargin().trim()),

                                    "en" to listOf("""
                                        <markup>
                                            <container>
                                                <carousel>
                                                    <block foreach="">
                                                        <headline>name in English</headline>
                                                    </block>
                                                </carousel>
                                            </container>
                                        </markup>
                                                """.trimMargin().trim(),
                                                    """
                                        <markup>
                                            <container>
                                                <!-- text element was replaced for label by the XSLT Transformer-->
                                                <label id="123">Based on the Input we can suggest following results:</label>
                                                <carousel>
                                                    <block foreach="">
                                                        <headline>name</headline>
                                                       <!-- text element was replaced for label by the XSLT Transformer-->
                                                         <label>address</label>
                                                        <!-- textInput element was replaced for text by the XSLT Transformer-->
                                                        <text name="textInputInEnglish" placeholder="type here..." regex="" value="foo" required="true" class="text"/>
                                                        <labelA>A</labelA>
                                                        <labelB>B</labelB>
                                                        <labelC>C</labelC>
                                                    </block>
                                                </carousel>
                                            <comment>Document was automatically migrated to version CANON-2.0.0 by a XLST Trasnformer: transform_2.0.0.xlst</comment>
                                            </container>
                                        </markup>
                                                """.trimMargin().trim())
                            )
            )
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} given XML: [${it["utterance"]}]") {
            val classUnderTest = XLSTUpgradeHandler()
            val transformedXml = classUnderTest.upgrade(it["utterance"] as Map<String,List<String>>, it["version"] as String)

            transformedXml.entries.forEach {utteranceEntry ->
                utteranceEntry.value.forEachIndexed { index, utterance ->
                    assertThat(utterance.replace("\\s".toRegex(), "")).isEqualTo((it["expectedUtterance"] as Map<String, List<String>>).get(utteranceEntry.key)!!.get(index).replace("\\s".toRegex(), ""))
                }
            }
        }
    }

}
