package canon.model

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class OverlaysTest {

    @Test
    fun testOverlayMapping() {
        val mapped = Overlays("testId", "testClass", "testTrigger",
                ArrayList()).toMap(HashMap(), mockk())

        assertEquals(1, mapped.size)
        assertEquals("testTrigger", mapped.get("trigger"))
    }
}
