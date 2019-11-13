package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SubmitTest {

    @Test
    fun testSubmitMapping() {
        val mapped = Submit("testId", "testClass", "testText", "testName").toMap(HashMap(), TestEvaluator())

        assertEquals(4, mapped.size)
        assertEquals("testText", mapped.get("text"))
        assertEquals("testName", mapped.get("name"))
    }
}
