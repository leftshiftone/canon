package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TextareaTest {

    @Test
    fun testTextareaMapping() {
        val mapped = Textarea("testId", "testClass", "testAriaLabel", "placeHolder", "testName",
                "testValue", false, 10, 9).toMap(HashMap(), TestEvaluator())

        assertEquals(9, mapped.size)
        assertEquals("placeHolder", mapped["placeholder"])
        assertEquals("testName", mapped["name"])
        assertEquals("testValue", mapped["value"])
        assertEquals(false, mapped["required"])
        assertEquals(10, mapped["rows"])
        assertEquals(9, mapped["cols"])
    }
}
