package canon.model

import canon.support.TestEvaluator
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SliderTest {

    @Test
    fun testSliderMapping() {
        val mapped = Slider("testId", "testClass", 1.0, 101.0, 1.0, 25.0,
                "testName", "testValue01, testValue02").toMap(HashMap(), TestEvaluator())

        assertEquals(6, mapped.size)
        assertEquals(1.0, mapped.get("min"))
        assertEquals(101.0, mapped.get("max"))
        assertEquals(1.0, mapped.get("step"))
        assertEquals(25.0, mapped.get("value"))
        assertEquals("testName", mapped.get("name"))
        //assertEquals(arrayOf("testValue01, testValue02"), mapped.get("values"))

    }
}
