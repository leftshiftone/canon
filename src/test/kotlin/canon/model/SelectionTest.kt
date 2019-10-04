package canon.model

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SelectionTest {

    @Test
    fun testSelectionMapping() {
        val mapped = Selection("testId", "testClass", "testName", 12,
                ArrayList()).toMap(HashMap(), mockk())

        assertEquals(2, mapped.size)
        assertEquals("testName", mapped.get("name"))
        assertEquals(12, mapped.get("countdownInSec"))
    }
}
