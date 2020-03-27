package canon.parser.xml.xlst

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

internal class TransformationManagementTest {


    @Test
    fun  `given a folder with no XLST files, no transformation is done`(){
        val classUnderTest = TransformationManagement("/xml/xlst")
        assertThat(classUnderTest.buildTransformationPath("1.2.0")).isNull()
    }


    @Test
    fun  `given a folder with one XLST older than the current version, no transformation is done`(){
        val classUnderTest = TransformationManagement("/xml/xlst/transformers")
        assertThat(classUnderTest.buildTransformationPath("3.2.0")).isNull()
    }

    @Test
    fun  `given a folder with one XLST newer than the current version, the transformerIterator has one transformation`(){
        val classUnderTest = TransformationManagement("/xml/xlst/transformers")
        assertThat(classUnderTest.buildTransformationPath("1.9.0")).isNotNull()
    }

    @Test
    fun  `given a folder with 4 newer XLST files than the current version, the transformerIterator contains all of them and in ASC order`() {
        val classUnderTest = TransformationManagement("/xml/xlst/transformers")
        val iterator = classUnderTest.buildTransformationPath("0.9.0")
        assertThat(iterator).isNotNull()
        assertThat(iterator!!.next().version).isEqualTo(SemanticVersion("1.0.0"))
        assertThat(iterator.next().version).isEqualTo(SemanticVersion("1.3.0"))
        assertThat(iterator.next().version).isEqualTo(SemanticVersion("1.5.0"))
        assertThat(iterator.next().version).isEqualTo(SemanticVersion("2.0.0"))
        assertThat(iterator.hasNext()).isFalse()
    }

    @Test
    fun  `given a folder with one older XLST files and one newer than the current version, the transformerIterator contains just the newer and in ASC order`(){
        val classUnderTest = TransformationManagement("/xml/xlst/transformers")
        val iterator = classUnderTest.buildTransformationPath("1.6.0")
        assertThat(iterator).isNotNull()
        assertThat(iterator!!.next().version).isEqualTo(SemanticVersion("2.0.0"))
        assertThat(iterator.hasNext()).isFalse()
    }

    @TestFactory
    fun `Transform a XML with multiple sequential transformers`() = listOf(
            mapOf(
                "name" to "Transform XML throw transformers: 1.0.0 , 1.3.0, 1.5.0 and 2.0.0 but no changes must be made",
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
            ),mapOf(
                "name" to "Transform XML throw transformers: 1.0.0 , 1.3.0, 1.5.0 and 2.0.0",
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
                            </container>
                        </markup>
                                """.trimMargin().trim()
            ),
            mapOf(
                    "name" to "Transform XML throw transformers: 1.5.0 and 2.0.0",
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
                            </container>
                        </markup>
                                """.trimMargin().trim()
            ),
            mapOf(
                    "name" to "Transform XML throw transformers: 2.0.0",
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
            val classUnderTest = TransformationManagement("/xml/xlst/transformers")
            val transformedXml = classUnderTest.transform(it["rawXml"] as String, it["version"] as String)
            assertThat(transformedXml.replace("\\s".toRegex(), "")).isEqualTo(it["expectedTransformedXml"]!!.replace("\\s".toRegex(), ""))

        }
    }

}
