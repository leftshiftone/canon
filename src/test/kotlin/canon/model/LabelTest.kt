package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class LabelTest {

    @Test
    fun testLabelMapping() {
        val mapped = Label("testId", "testClass", "testAriaLabel", "testText").toMap(HashMap(), TestEvaluator())

        assertEquals(4, mapped.size)
        assertEquals("testText", mapped["text"])
    }

    @Test
    fun `mapping with expression`() {
        val mapped = Label("testId", "testClass", "testAriaLabel", "{{\$foo}}").toMap(HashMap(), TestEvaluator())

        assertEquals(4, mapped.size)
        assertEquals("\$foo", mapped["text"])
    }
}
