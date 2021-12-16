package canon.parser.map.strategy

import canon.model.Choice
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ChoiceStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Choice("id", "class", "ariaLabel", "name", "text", "true", emptyList()), ChoiceStrategy()))
}