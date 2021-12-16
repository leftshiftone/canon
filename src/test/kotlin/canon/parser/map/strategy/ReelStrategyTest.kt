package canon.parser.map.strategy

import canon.model.Reel
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ReelStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Reel("id", "class", "ariaLabel", "name", emptyList()), ReelStrategy()))
}