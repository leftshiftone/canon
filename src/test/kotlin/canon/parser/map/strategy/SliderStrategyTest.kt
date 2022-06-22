package canon.parser.map.strategy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SliderStrategyTest {

    @Test
    fun testParse() {
        val map = mapOf<String, Any>("id" to "id", "class" to "class", "ariaLabel" to "ariaLabel", "min" to 1.0, "max" to 10.0, "step" to 1.0,
                "value" to 5.0, "name" to "name", "values" to "value1, value2, value3")
        val mappedSlider = SliderStrategy().parse(map) { emptyList()}

        assertEquals(map["id"], mappedSlider.id)
        assertEquals(map["class"], mappedSlider.`class`)
        assertEquals(map["ariaLabel"], mappedSlider.ariaLabel)
        assertEquals(map["min"], mappedSlider.min)
        assertEquals(map["max"], mappedSlider.max)
        assertEquals(map["step"], mappedSlider.step)
        assertEquals(map["value"], mappedSlider.value)
        assertEquals(map["name"], mappedSlider.name)
        assertEquals("value1, value2, value3", mappedSlider.values)
    }
}