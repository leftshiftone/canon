package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BoldTest {

    @Test
    fun testBoldMapping() {
        val mapped = Bold("testId", "testClass", "testAriaLabel", "testText").toMap(HashMap(), TestEvaluator())

        assertEquals(4, mapped.size)
        assertEquals("testText", mapped.get("text"))
    }
}
