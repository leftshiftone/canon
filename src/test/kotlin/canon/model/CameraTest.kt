package canon.model

import canon.api.IRenderable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class CameraTest {

    @Test
    fun testCameraMapping() {
        val mapped = Camera("testId", "testClass", "testName",
                true, 1.0, ArrayList<IRenderable>()).toMap(HashMap<String, Any>())

        assertEquals(3, mapped.size)
        assertEquals(true, mapped.get("required"))
        assertEquals(1.0, mapped.get("maxCompressSize"))
        assertEquals("testName", mapped.get("name"))
    }
}