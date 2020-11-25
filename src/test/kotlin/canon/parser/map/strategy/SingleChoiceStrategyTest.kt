package canon.parser.map.strategy

import canon.model.SingleChoice
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SingleChoiceStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(SingleChoice("id", "class", "name", true, true, emptyList()), SingleChoiceStrategy()))
}