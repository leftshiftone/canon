package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class EmailTest {

    @Test
    fun testEmailMapping() {
        val mapped = Email("testId", "testClass", "placeHolder", true,
                "testName", "testValue").toMap(HashMap(), TestEvaluator())

        assertEquals(6, mapped.size)
        assertEquals("placeHolder", mapped.get("placeholder"))
        assertEquals(true, mapped.get("required"))
        assertEquals("testName", mapped.get("name"))
        assertEquals("testValue", mapped.get("value"))
    }
}
