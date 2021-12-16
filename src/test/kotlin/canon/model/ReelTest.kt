package canon.model

import canon.api.IRenderable
import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ReelTest {

    @Test
    fun testReelMapping() {
        val mapped = Reel("testId", "testClass", "testAriaLabel", "testName",
                ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(4, mapped.size)
        assertEquals("testName", mapped["name"])
    }
}
