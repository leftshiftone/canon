package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ReelValueTest {

    @Test
    fun testReelValueMapping() {
        val mapped = ReelValue("testId", "testClass", "testValue", "valuetype").toMap(HashMap(), TestEvaluator())

        assertEquals(4, mapped.size)
        assertEquals("testValue", mapped.get("value"))
        assertEquals("valuetype", mapped.get("valueType"))
    }
}
