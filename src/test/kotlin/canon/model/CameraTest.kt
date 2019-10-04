package canon.model

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class CameraTest {

    @Test
    fun testCameraMapping() {
        val mapped = Camera("testId", "testClass", "testName",
                true, 1.0, ArrayList()).toMap(HashMap(), mockk())

        assertEquals(3, mapped.size)
        assertEquals(true, mapped.get("required"))
        assertEquals(1.0, mapped.get("maxCompressSize"))
        assertEquals("testName", mapped.get("name"))
    }
}
