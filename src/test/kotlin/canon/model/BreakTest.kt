package canon.model

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BreakTest {

    @Test
    fun testBreakMapping() {
        val mapped = Break("testId", "testClass").toMap(HashMap(), mockk())

        assertEquals(0, mapped.size)
    }
}
