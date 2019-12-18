package canon.model

import canon.support.TestEvaluator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class MultipleChoiceTest {

    @Test
    fun testMultipleChoiceMapping() {
        val mapped = MultipleChoice("testId", "testClass", "testName", false, true,
                ArrayList()).toMap(HashMap(), TestEvaluator())

        assertThat(mapped.size).isEqualTo(5)
        assertThat(mapped["name"]).isEqualTo("testName")
        assertThat(mapped["sieve"]).isEqualTo(false)
    }
}
