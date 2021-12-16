package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SelectionTest {

    @Test
    fun testSelectionMapping() {
        val mapped = Selection("testId", "testClass", "testAriaLabel", "testName", 12,
                ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(5, mapped.size)
        assertEquals("testName", mapped["name"])
        assertEquals(12, mapped["countdownInSec"])
    }
}
