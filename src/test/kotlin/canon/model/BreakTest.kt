package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BreakTest {

    @Test
    fun testBreakMapping() {
        val mapped = Break("testId", "testClass").toMap(HashMap<String, Any>())

        assertEquals(0, mapped.size)
    }
}