package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TriggerTest {

    @Test
    fun testTriggerMapping() {
        val mapped = Trigger("testId", "testClass", "testAriaLabel", "testName","testText").toMap(HashMap(), TestEvaluator())

        assertEquals(5, mapped.size)
        assertEquals("testName", mapped["name"])
        assertEquals("testText", mapped["text"])
    }
}
