package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TriggerTest {

    @Test
    fun testTriggerMapping() {
        val mapped = Trigger("testId", "testClass", "testName","testText").toMap(HashMap())

        assertEquals(2, mapped.size)
        assertEquals("testName", mapped.get("name"))
        assertEquals("testText", mapped.get("text"))
    }
}
