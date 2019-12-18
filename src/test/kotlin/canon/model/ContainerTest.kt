package canon.model

import canon.support.TestEvaluator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class ContainerTest {

    @Test
    fun testContainerMapping() {
        val mapped = Container("testId", "testClass", "testName",
                ArrayList()).toMap(HashMap(), TestEvaluator())

        assertThat(mapped.size).isEqualTo(3)
        assertThat(mapped["name"]).isEqualTo("testName")
    }
}
