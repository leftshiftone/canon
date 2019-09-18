package canon.model

import canon.api.IRenderable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class OverlayTest {

    @Test
    fun testOverlayMapping() {
        val mapped = Overlay("testId", "testClass", "testTrigger",
                ArrayList<IRenderable>()).toMap(HashMap<String, Any>())

        assertEquals(1, mapped.size)
        assertEquals("testTrigger", mapped.get("trigger"))
    }
}