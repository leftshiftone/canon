package canon.parser.xml.upgrade.xlst

import canon.parser.xml.upgrade.SemanticVersion
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class SemanticVersionTest {


    @TestFactory
    fun `qualifier test`() = listOf(
            mapOf("name" to "Creating Semantic Version for 1.2.3",
                    "givenStringWithVersion" to "1.2.3",
                    "expectedVersion" to SemanticVersion("1", "2", "3")),
            mapOf("name" to "Creating Semantic Version for 3.2.1",
                    "givenStringWithVersion" to "3.2.1",
                    "expectedVersion" to SemanticVersion("3", "2", "1")),
            mapOf("name" to "Creating Semantic Version for 3.2.1-SNAPSHOT",
                    "givenStringWithVersion" to "3.2.1-SNAPSHOT",
                    "expectedVersion" to SemanticVersion("3", "2", "1"))
    ).map {
        DynamicTest.dynamicTest("Name: ${it["name"]} given String: [${it["givenStringWithVersion"]}] -> ${it["expectedVersion"]}") {
            Assertions.assertThat(SemanticVersion(it["givenStringWithVersion"] as String)).isEqualTo(it["expectedVersion"])
        }
    }

}