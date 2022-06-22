package canon.parser.map.strategy

import canon.model.Selection
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SelectionStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Selection("id", "class", "ariaLabel", "name", 10, emptyList()), SelectionStrategy()))
}