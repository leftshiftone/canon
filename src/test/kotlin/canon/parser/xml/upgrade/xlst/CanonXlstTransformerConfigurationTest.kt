package canon.parser.xml.upgrade.xlst

import canon.parser.xml.upgrade.CanonXlstTransformerConfiguration
import canon.parser.xml.upgrade.SemanticVersion
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class CanonXlstTransformerConfigurationTest {


    @TestFactory
    fun `parse version`() = listOf(
            mapOf("name" to "Creating Semantic Version for 1.2.3",
                    "givenStringWithVersion" to "file:/home/agea/repo/core/gaia/gaia-boot/build/libs/gaia-boot-1.5.0-SNAPSHOT.jar!/BOOT-INF/lib/canon-1.9.0-SNAPSHOT.jar!/xml/xlst/transformers/transform_2.0.0.xlst",
                    "expectedVersion" to SemanticVersion("2.0.0")),
            mapOf("name" to "Creating Semantic Version for 3.2.1",
                    "givenStringWithVersion" to "/xml/xlst/transformers/transform_3.2.1.xlst",
                    "expectedVersion" to SemanticVersion("3.2.1")),
            mapOf("name" to "Creating Semantic Version for 3.2.1-SNAPSHOT",
                    "givenStringWithVersion" to "/xml/xlst/transformers/transform_3.2.1-SNAPSHOT.xlst",
                    "expectedVersion" to SemanticVersion("3.2.1"))
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} given String: [${it["givenStringWithVersion"]}] -> ${it["expectedVersion"]}") {
            Assertions.assertThat(CanonXlstTransformerConfiguration(it["givenStringWithVersion"] as String).version).isEqualTo(it["expectedVersion"])
        }
    }

    @TestFactory
    fun `parse transformer location`() = listOf(
            mapOf("name" to "Creating Semantic Version for 1.2.3",
                    "givenTransformerLocation" to "file:/home/agea/repo/core/gaia/gaia-boot/build/libs/gaia-boot-1.5.0-SNAPSHOT.jar!/BOOT-INF/lib/canon-1.9.0-SNAPSHOT.jar!/xml/xlst/transformers/transform_2.0.0.xlst",
                    "expectedLocation" to "/xml/xlst/transformers/transform_2.0.0.xlst"),
            mapOf("name" to "Creating Semantic Version for 3.2.1",
                    "givenTransformerLocation" to "/xml/xlst/transformers/transform_3.2.1.xlst",
                    "expectedLocation" to "/xml/xlst/transformers/transform_3.2.1.xlst")
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} given String: [${it["givenTransformerLocation"]}] -> ${it["expectedVersion"]}") {
            Assertions.assertThat(CanonXlstTransformerConfiguration(it["givenTransformerLocation"] as String).transformerLocation).isEqualTo(it["expectedLocation"])
        }
    }

}