package canon.model

import canon.api.IRenderable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class OverlaysTest {

    @Test
    fun testOverlayMapping() {
        val mapped = Overlays("testId", "testClass", "testTrigger",
                ArrayList<IRenderable>()).toMap(HashMap<String, Any>())

        assertEquals(1, mapped.size)
        assertEquals("testTrigger", mapped.get("trigger"))
    }
}