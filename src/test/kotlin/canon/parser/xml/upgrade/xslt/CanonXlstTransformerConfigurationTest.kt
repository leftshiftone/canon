package canon.parser.xml.upgrade.xslt

import canon.parser.xml.upgrade.SemanticVersion
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class CanonXsltTransformerConfigurationTest {


    @TestFactory
    fun `parse version`() = listOf(
            mapOf("name" to "Creating Semantic Version for 1.2.3",
                    "givenStringWithVersion" to "file:/home/agea/repo/core/gaia/gaia-boot/build/libs/gaia-boot-1.5.0-SNAPSHOT.jar!/BOOT-INF/lib/canon-1.9.0-SNAPSHOT.jar!/xml/xslt/transformers/transform_2.0.0.xslt",
                    "expectedVersion" to SemanticVersion("2.0.0")),
            mapOf("name" to "Creating Semantic Version for 3.2.1",
                    "givenStringWithVersion" to "/xml/xslt/transformers/transform_3.2.1.xslt",
                    "expectedVersion" to SemanticVersion("3.2.1")),
            mapOf("name" to "Creating Semantic Version for 3.2.1-SNAPSHOT",
                    "givenStringWithVersion" to "/xml/xslt/transformers/transform_3.2.1-SNAPSHOT.xslt",
                    "expectedVersion" to SemanticVersion("3.2.1"))
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} given String: [${it["givenStringWithVersion"]}] -> ${it["expectedVersion"]}") {
            Assertions.assertThat(XSLTTransformerConfiguration(it["givenStringWithVersion"] as String).version).isEqualTo(it["expectedVersion"])
        }
    }

    @TestFactory
    fun `parse transformer location`() = listOf(
            mapOf("name" to "Creating Semantic Version for 1.2.3",
                    "givenTransformerLocation" to "file:/home/agea/repo/core/gaia/gaia-boot/build/libs/gaia-boot-1.5.0-SNAPSHOT.jar!/BOOT-INF/lib/canon-1.9.0-SNAPSHOT.jar!/xml/xslt/transformers/transform_2.0.0.xslt",
                    "expectedLocation" to "/xml/xslt/transformers/transform_2.0.0.xslt"),
            mapOf("name" to "Creating Semantic Version for 3.2.1",
                    "givenTransformerLocation" to "/xml/xslt/transformers/transform_3.2.1.xslt",
                    "expectedLocation" to "/xml/xslt/transformers/transform_3.2.1.xslt")
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} given String: [${it["givenTransformerLocation"]}] -> ${it["expectedVersion"]}") {
            Assertions.assertThat(XSLTTransformerConfiguration(it["givenTransformerLocation"] as String).transformerLocation).isEqualTo(it["expectedLocation"])
        }
    }

}