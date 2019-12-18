package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat


class ChoiceTest {

    @Test
    fun testChoiceMapping() {
        val mapped = Choice("testId", "testClass", "choiceName","testText", "false",
                ArrayList()).toMap(HashMap(), TestEvaluator())

        assertThat(mapped.size).isEqualTo(5)
        assertThat(mapped.get("text")).isEqualTo("testText")
        assertThat(mapped.get("selected")).isEqualTo(false)
    }
}
