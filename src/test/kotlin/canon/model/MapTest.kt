package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class MapTest {

    @Test
    fun testMapMapping() {
        val mapped = Map("testId", "testClass", "testName", "testSrc",
                "type", 123.0, -170.0, "testRoute",
                false, true, 12, 1).toMap(HashMap(), TestEvaluator())

        assertEquals(12, mapped.size)
        assertEquals("testName", mapped["name"])
        assertEquals("testSrc", mapped["src"])
        assertEquals("type", mapped["mapType"])
        assertEquals(123.0, mapped["centerLng"])
        assertEquals(-170.0, mapped["centerLat"])
        assertEquals("testRoute", mapped["route"])
        assertEquals(false, mapped["centerBrowserLocation"])
        assertEquals(true, mapped["required"])
        assertEquals(12, mapped["zoom"])
        assertEquals(1, mapped["maxSelections"])
    }
}
