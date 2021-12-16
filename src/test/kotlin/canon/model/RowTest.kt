package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class RowTest {

    @Test
    fun testRowMapping() {
        val mapped = Row("testId", "testAriaLabel", "testClass", ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(3, mapped.size)
    }
}
