package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class CalendarTest {

    @Test
    fun testCalendarMapping() {
        val mapped = Calendar("testId", "testClass", "testName").toMap(HashMap(), TestEvaluator())

        assertEquals(3, mapped.size)
        assertEquals("testName", mapped.get("name"))
    }
}
