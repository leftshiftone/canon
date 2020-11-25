package canon.parser.map.strategy

import canon.model.Selectable
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SelectableStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Selectable("id", "class", "name", emptyList()), SelectableStrategy()))
}