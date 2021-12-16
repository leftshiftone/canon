package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SubmitTest {

    @Test
    fun testSubmitMapping() {
        val mapped = Submit("testId", "testClass", "testAriaLabel", "testText", "testName").toMap(HashMap(), TestEvaluator())

        assertEquals(5, mapped.size)
        assertEquals("testText", mapped["text"])
        assertEquals("testName", mapped["name"])
    }
}
