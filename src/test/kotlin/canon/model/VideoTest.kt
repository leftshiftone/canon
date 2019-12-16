package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class VideoTest {

    @Test
    fun testVideoMapping() {
        val mapped = Video("testId", "testClass", "testSrc").toMap(HashMap(), TestEvaluator())

        assertEquals(3, mapped.size)
        assertEquals("testSrc", mapped.get("src"))
    }
}
