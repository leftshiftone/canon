package canon.model

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TriggerTest {

    @Test
    fun testTriggerMapping() {
        val mapped = Trigger("testId", "testClass", "testName","testText").toMap(HashMap(), mockk())

        assertEquals(2, mapped.size)
        assertEquals("testName", mapped.get("name"))
        assertEquals("testText", mapped.get("text"))
    }
}
