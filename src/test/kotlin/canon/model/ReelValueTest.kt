package canon.model

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ReelValueTest {

    @Test
    fun testReelValueMapping() {
        val mapped = ReelValue("testId", "testClass", "testValue", "valuetype").toMap(HashMap(), mockk())

        assertEquals(2, mapped.size)
        assertEquals("testValue", mapped.get("value"))
        assertEquals("valuetype", mapped.get("valueType"))
    }
}
