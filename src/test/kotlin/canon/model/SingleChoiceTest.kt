package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat



class SingleChoiceTest {

    @Test
    fun testSingleChoiceMapping() {
        val mapped = SingleChoice(id ="testId", `class` = "testClass", name =  "testName", sieve =  false,
               renderables =  ArrayList()).toMap(HashMap(), TestEvaluator())

        assertThat(mapped.size).isEqualTo(5)
        assertThat(mapped.get("name")).isEqualTo("testName")
        assertThat(mapped.get("sieve")).isEqualTo(false)
        assertThat(mapped.get("required")).isEqualTo(false)
    }
}
