package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class MapTest {

    @Test
    fun testMapMapping() {
        val mapped = Map("testId", "testClass", "testName", "testSrc",
                "type", "centerlng", "centerlat", true,
                false, true, 12,1).toMap(HashMap(), TestEvaluator())

        assertEquals(11, mapped.size)
        assertEquals("testName", mapped["name"])
        assertEquals("testSrc", mapped["src"])
        assertEquals("type", mapped["mapType"])
        assertEquals("centerlng", mapped["centerLng"])
        assertEquals("centerlat", mapped["centerLat"])
        assertEquals(true, mapped["exact"])
        assertEquals(false, mapped["centerBrowserLocation"])
        assertEquals(true, mapped["required"])
        assertEquals(12, mapped["zoom"])
        assertEquals(1, mapped["maxSelections"])
    }
}
