package canon.parser.map.strategy

import canon.model.Item
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ItemStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Item("id", "class", "ariaLabel", emptyList()), ItemStrategy()))
}