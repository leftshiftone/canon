package canon.model

import canon.support.TestEvaluator
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BoldTest {

    @Test
    fun testBoldMapping() {
        val mapped = Bold("testId", "testClass", "testText").toMap(HashMap(), TestEvaluator())

        assertEquals(1, mapped.size)
        assertEquals("testText", mapped.get("text"))
    }
}
