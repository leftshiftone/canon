package canon.model

import canon.support.TestEvaluator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class ContainerTest {

    @Test
    fun testContainerMapping() {
        val mapped = Container("testId", "testClass", "testAriaLabel", "testName",
                ArrayList()).toMap(HashMap(), TestEvaluator())

        assertThat(mapped.size).isEqualTo(4)
        assertThat(mapped["name"]).isEqualTo("testName")
    }
}
