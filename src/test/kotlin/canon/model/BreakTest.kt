package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BreakTest {

    @Test
    fun testBreakMapping() {
        val mapped = Break("testId", "testClass").toMap(HashMap(), TestEvaluator())

        assertEquals(2, mapped.size)
    }
}
