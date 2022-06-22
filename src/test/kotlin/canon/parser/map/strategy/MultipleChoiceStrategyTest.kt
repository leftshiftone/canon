package canon.parser.map.strategy

import canon.model.MultipleChoice
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MultipleChoiceStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(MultipleChoice("id", "class", "ariaLabel", "name", true, true, emptyList()), MultipleChoiceStrategy()))
}