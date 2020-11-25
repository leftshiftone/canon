package canon.parser.map.strategy

import canon.model.Basket
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class BasketStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Basket("id", "class", "name", true, emptyList()), BasketStrategy()))
}