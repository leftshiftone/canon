package canon.parser.map.strategy

import canon.model.Break
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ForeachStrategyTest {

    @Test
    fun testParse() {
        val map = mapOf<String, Any>("forEachStmt" to "\$1 in \$10", "renderable" to Break("id", "class", "ariaLabel"))
        val mappedForeach = ForeachStrategy().parse(map) { emptyList() }

        assertEquals(map["forEachStmt"], mappedForeach.forEachStmt)
        assertEquals(map["renderable"], mappedForeach.renderable)
    }
}