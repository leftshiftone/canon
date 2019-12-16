package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TriggerTest {

    @Test
    fun testTriggerMapping() {
        val mapped = Trigger("testId", "testClass", "testName","testText").toMap(HashMap(), TestEvaluator())

        assertEquals(4, mapped.size)
        assertEquals("testName", mapped.get("name"))
        assertEquals("testText", mapped.get("text"))
    }
}
