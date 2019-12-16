package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class OverlaysTest {

    @Test
    fun testOverlayMapping() {
        val mapped = Overlays("testId", "testClass", "testTrigger",
                ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(3, mapped.size)
        assertEquals("testTrigger", mapped.get("trigger"))
    }
}
