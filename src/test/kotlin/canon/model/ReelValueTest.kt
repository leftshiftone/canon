package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ReelValueTest {

    @Test
    fun testReelValueMapping() {
        val mapped = ReelValue("testId", "testClass", "testAriaLabel", "testValue", "valuetype").toMap(HashMap(), TestEvaluator())

        assertEquals(5, mapped.size)
        assertEquals("testValue", mapped["value"])
        assertEquals("valuetype", mapped["valueType"])
    }
}
