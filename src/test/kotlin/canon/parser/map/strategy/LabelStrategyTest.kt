package canon.parser.map.strategy

import canon.model.Label
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class LabelStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Label("id", "class", "ariaLabel", "text"), LabelStrategy()))
}