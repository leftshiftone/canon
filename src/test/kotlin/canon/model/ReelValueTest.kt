package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ReelValueTest {

    @Test
    fun testReelValueMapping() {
        val mapped = ReelValue("testId", "testClass", "testValue", "valuetype").toMap(HashMap<String, Any>())

        assertEquals(2, mapped.size)
        assertEquals("testValue", mapped.get("value"))
        assertEquals("valuetype", mapped.get("valueType"))
    }
}