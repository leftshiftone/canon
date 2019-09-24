package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ItalicTest {

    @Test
    fun testItalicMapping() {
        val mapped = Italic("testId", "testClass", "testText").toMap(HashMap())

        assertEquals(1, mapped.size)
        assertEquals("testText", mapped.get("text"))
    }
}
