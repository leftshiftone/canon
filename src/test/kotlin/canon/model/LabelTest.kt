package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class LabelTest {

    @Test
    fun testLabelMapping() {
        val mapped = Label("testId", "testClass", "testText").toMap(HashMap(), TestEvaluator())

        assertEquals(3, mapped.size)
        assertEquals("testText", mapped.get("text"))
    }

    @Test
    fun `mapping with expression`() {
        val mapped = Label("testId", "testClass", "{{\$foo}}").toMap(HashMap(), TestEvaluator())

        assertEquals(3, mapped.size)
        assertEquals("\$foo", mapped["text"])
    }
}
