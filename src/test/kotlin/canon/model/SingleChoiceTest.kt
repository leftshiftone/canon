package canon.model

import canon.support.TestEvaluator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class SingleChoiceTest {

    @Test
    fun testSingleChoiceMapping() {
        val mapped = SingleChoice("testId", "testClass", "testAriaLabel", "testName", false, true,
                renderables = ArrayList()).toMap(HashMap(), TestEvaluator())

        assertThat(mapped.size).isEqualTo(6)
        assertThat(mapped["name"]).isEqualTo("testName")
        assertThat(mapped["sieve"]).isEqualTo(false)
        assertThat(mapped["required"]).isEqualTo(true)
    }
}
