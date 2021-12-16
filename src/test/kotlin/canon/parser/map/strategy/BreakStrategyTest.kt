package canon.parser.map.strategy

import canon.model.Break
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class BreakStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Break("id", "class",  "ariaLabel"), BreakStrategy()))
}