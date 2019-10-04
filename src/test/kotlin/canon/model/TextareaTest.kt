package canon.model

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TextareaTest {

    @Test
    fun testTextareaMapping() {
        val mapped = Textarea("testId", "testClass", "placeHolder", "testName",
                "testValue", false, 10, 9).toMap(HashMap(), mockk())

        assertEquals(6, mapped.size)
        assertEquals("placeHolder", mapped.get("placeholder"))
        assertEquals("testName", mapped.get("name"))
        assertEquals("testValue", mapped.get("value"))
        assertEquals(false, mapped.get("required"))
        assertEquals(10, mapped.get("rows"))
        assertEquals(9, mapped.get("cols"))
    }
}
