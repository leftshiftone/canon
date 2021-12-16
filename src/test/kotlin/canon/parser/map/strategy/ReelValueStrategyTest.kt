package canon.parser.map.strategy

import canon.model.ReelValue
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ReelValueStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(ReelValue("id", "class", "ariaLabel", "value", "valueType"), ReelValueStrategy()))
}