package canon.parser.map.strategy

import canon.model.Col
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ColStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Col("id", "class", "ariaLabel", emptyList()), ColStrategy()))
}