package canon.model

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SubmitTest {

    @Test
    fun testSubmitMapping() {
        val mapped = Submit("testId", "testClass", "testText", "testName").toMap(HashMap(), mockk())

        assertEquals(2, mapped.size)
        assertEquals("testText", mapped.get("text"))
        assertEquals("testName", mapped.get("name"))
    }
}
