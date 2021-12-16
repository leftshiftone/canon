package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TextTest {

    @Test
    fun testTextMapping() {
        val mapped = Text("testId", "testClass", "testAriaLabel", ".*", "testPlaceholder",
                true, "testName", "testValue").toMap(HashMap(), TestEvaluator())

        assertEquals(8, mapped.size)
        assertEquals("testPlaceholder", mapped["placeholder"])
        assertEquals(true, mapped["required"])
        assertEquals("testName", mapped["name"])
        assertEquals("testValue", mapped["value"])
    }
}
