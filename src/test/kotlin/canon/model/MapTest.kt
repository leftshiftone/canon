package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class MapTest {

    @Test
    fun testMapMapping() {
        val mapped = Map("testId", "testClass", "testName", "testSrc",
                "type", "centerlng", "centerlat", true,
                false, true).toMap(HashMap<String, Any>())

        assertEquals(8, mapped.size)
        assertEquals("testName", mapped.get("name"))
        assertEquals("testSrc", mapped.get("src"))
        assertEquals("type", mapped.get("mapType"))
        assertEquals("centerlng", mapped.get("centerLng"))
        assertEquals("centerlat", mapped.get("centerLat"))
        assertEquals(true, mapped.get("exact"))
        assertEquals(false, mapped.get("centerBrowserLocation"))
        assertEquals(true, mapped.get("required"))
    }
}