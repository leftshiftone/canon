package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ItalicTest {

    @Test
    fun testItalicMapping() {
        val mapped = Italic("testId", "testClass", "testAriaLabel", "testText").toMap(HashMap(), TestEvaluator())

        assertEquals(4, mapped.size)
        assertEquals("testText", mapped["text"])
    }
}
