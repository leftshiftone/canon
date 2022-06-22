package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ColTest {

    @Test
    fun testColMapping() {
        val mapped = Col("testId", "testAriaLabel", "testClass", ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(3, mapped.size)
    }
}
