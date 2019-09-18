package canon.model

import canon.api.IRenderable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SmallDeviceTest {

    @Test
    fun testSmallDeviceMapping() {
        val mapped = SmallDevice("testId", "testClass", "testName",
                ArrayList<IRenderable>()).toMap(HashMap<String, Any>())

        assertEquals(1, mapped.size)
        assertEquals("testName", mapped.get("name"))
    }
}