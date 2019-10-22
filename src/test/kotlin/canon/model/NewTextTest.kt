package canon.model

import canon.support.TestEvaluator
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class NewTextTest {

    @Test
    fun testTextMapping() {
        val mapped = NewText("testId", "testClass", "regEx", "placeHolder", false,
                "testName", "testValue").toMap(HashMap(), TestEvaluator())

        assertEquals(5, mapped.size)
        assertEquals("regEx", mapped.get("regex"))
        assertEquals("placeHolder", mapped.get("placeholder"))
        assertEquals(false, mapped.get("required"))
        assertEquals("testName", mapped.get("name"))
        assertEquals("testValue", mapped.get("value"))
    }
}
