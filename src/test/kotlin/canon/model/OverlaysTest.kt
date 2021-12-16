package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class OverlaysTest {

    @Test
    fun testOverlayMapping() {
        val mapped = Overlays("testId", "testClass", "testAriaLabel", "testTrigger",
                ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(4, mapped.size)
        assertEquals("testTrigger", mapped["trigger"])
    }
}
