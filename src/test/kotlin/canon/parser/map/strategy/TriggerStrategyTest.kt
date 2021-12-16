package canon.parser.map.strategy

import canon.model.Trigger
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TriggerStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Trigger("id", "class", "ariaLabel", "name", "text"), TriggerStrategy()))
}