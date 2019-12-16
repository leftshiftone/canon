package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ColTest {

    @Test
    fun testColMapping() {
        val mapped = Col("testId", "testClass", ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(2, mapped.size)
    }
}
