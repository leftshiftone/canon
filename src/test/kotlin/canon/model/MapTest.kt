package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class MapTest {

    @Test
    fun testMapMapping() {
        val mapped = Map("testId", "testClass", "testName", "testSrc",
                "type", 123.0, -170.0, "45.3,46.8;45.8,44.7",
                false, true, 12, 1).toMap(HashMap(), TestEvaluator())

        assertEquals(12, mapped.size)
        assertEquals("testName", mapped["name"])
        assertEquals("testSrc", mapped["src"])
        assertEquals("type", mapped["mapType"])
        assertEquals(123.0, mapped["centerLng"])
        assertEquals(-170.0, mapped["centerLat"])
        assertEquals("45.3,46.8;45.8,44.7", mapped["routePoints"])
        assertEquals(false, mapped["centerBrowserLocation"])
        assertEquals(true, mapped["required"])
        assertEquals(12, mapped["zoom"])
        assertEquals(1, mapped["maxSelections"])
    }
}
