package canon.model

import canon.api.IRenderable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SelectionTest {

    @Test
    fun testSelectionMapping() {
        val mapped = Selection("testId", "testClass", "testName", 12,
                ArrayList<IRenderable>()).toMap(HashMap<String, Any>())

        assertEquals(2, mapped.size)
        assertEquals("testName", mapped.get("name"))
        assertEquals(12, mapped.get("countdownInSec"))
    }
}