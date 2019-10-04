package canon.model

import canon.api.IRenderable
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ReelTest {

    @Test
    fun testReelMapping() {
        val mapped = Reel("testId", "testClass", "testName",
                ArrayList<IRenderable>()).toMap(HashMap(), mockk())

        assertEquals(1, mapped.size)
        assertEquals("testName", mapped.get("name"))
    }
}
