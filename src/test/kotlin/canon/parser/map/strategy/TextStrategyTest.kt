package canon.parser.map.strategy

import canon.model.Text
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TextStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Text("id", "class", "ariaLabel", "regex", "placeholder", true, "name", "value"), TextStrategy()))
}