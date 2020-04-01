package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TextTest {

    @Test
    fun testTextMapping() {
        val mapped = Text("testId", "testClass", ".*", "testPlaceholder",
                true, "testName", "testValue").toMap(HashMap(), TestEvaluator())

        assertEquals(7, mapped.size)
        assertEquals("testPlaceholder", mapped.get("placeholder"))
        assertEquals(true, mapped.get("required"))
        assertEquals("testName", mapped.get("name"))
        assertEquals("testValue", mapped.get("value"))
    }
}
