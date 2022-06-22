package canon.parser.map.strategy

import canon.model.Break
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class IfStrategyTest {

    @Test
    fun testParse() {
        val map = mapOf<String, Any>("expression" to "expression", "renderable" to Break(null, null, null))
        val mappedIf = IfStrategy().parse(map) { emptyList() }

        assertEquals(map["expression"], mappedIf.expression)
        assertEquals(map["renderable"], mappedIf.renderable)
    }
}