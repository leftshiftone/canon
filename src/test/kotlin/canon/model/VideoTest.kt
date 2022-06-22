package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class VideoTest {

    @Test
    fun testVideoMapping() {
        val mapped = Video("testId", "testClass", "testAriaLabel", "testSrc").toMap(HashMap(), TestEvaluator())

        assertEquals(4, mapped.size)
        assertEquals("testSrc", mapped["src"])
    }
}
