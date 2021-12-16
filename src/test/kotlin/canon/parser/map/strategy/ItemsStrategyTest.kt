package canon.parser.map.strategy

import canon.model.Items
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ItemsStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Items("id", "class", "ariaLabel", true, emptyList()), ItemsStrategy()))
}