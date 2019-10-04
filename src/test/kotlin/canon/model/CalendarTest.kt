package canon.model

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class CalendarTest {

    @Test
    fun testCalendarMapping() {
        val mapped = Calendar("testId", "testClass", "testName").toMap(HashMap(), mockk())

        assertEquals(1, mapped.size)
        assertEquals("testName", mapped.get("name"))
    }
}
