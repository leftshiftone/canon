package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class OverlayTest {

    @Test
    fun testOverlayMapping() {
        val mapped = Overlay("testId", "testClass", "testTrigger",
                ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(3, mapped.size)
        assertEquals("testTrigger", mapped.get("trigger"))
    }
}
