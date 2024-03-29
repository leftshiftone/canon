package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class CameraTest {

    @Test
    fun testCameraMapping() {
        val mapped = Camera("testId", "testClass", "testAriaLabel", "testName",
                true, 1.0, ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(6, mapped.size)
        assertEquals(true, mapped.get("required"))
        assertEquals(1.0, mapped.get("maxCompressSize"))
        assertEquals("testName", mapped.get("name"))
    }
}
