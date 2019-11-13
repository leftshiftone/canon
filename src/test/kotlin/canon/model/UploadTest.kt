package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class UploadTest {

    @Test
    fun testUploadMapping() {
        val mapped = Upload("testId", "testClass", "jpg, png","testName",
                "testText", 5.0, 1.0).toMap(HashMap(), TestEvaluator())

        assertEquals(7, mapped.size)
        assertEquals("jpg, png", mapped.get("accept"))
        assertEquals("testName", mapped.get("name"))
        assertEquals("testText", mapped.get("text"))
        assertEquals(5.0, mapped.get("maxSize"))
        assertEquals(1.0, mapped.get("maxCompressionSize"))
    }
}
