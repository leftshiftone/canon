package canon.model

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ItalicTest {

    @Test
    fun testItalicMapping() {
        val mapped = Italic("testId", "testClass", "testText").toMap(HashMap(), mockk())

        assertEquals(1, mapped.size)
        assertEquals("testText", mapped.get("text"))
    }
}
