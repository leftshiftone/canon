package canon.model

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class OverlayTest {

    @Test
    fun testOverlayMapping() {
        val mapped = Overlay("testId", "testClass", "testTrigger",
                ArrayList()).toMap(HashMap(), mockk())

        assertEquals(1, mapped.size)
        assertEquals("testTrigger", mapped.get("trigger"))
    }
}
