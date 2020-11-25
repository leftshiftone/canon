package canon.parser.map.strategy

import canon.model.Italic
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ItalicStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Italic("id", "class", "text"), ItalicStrategy()))
}