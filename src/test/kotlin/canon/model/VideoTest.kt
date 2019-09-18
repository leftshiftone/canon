package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class VideoTest {

    @Test
    fun testVideoMapping() {
        val mapped = Video("testId", "testClass", "testSrc").toMap(HashMap<String, Any>())

        assertEquals(1, mapped.size)
        assertEquals("testSrc", mapped.get("src"))
    }
}