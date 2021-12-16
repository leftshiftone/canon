package canon.parser.map.strategy

import canon.model.Textarea
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TextareaStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Textarea("id", "class", "ariaLabel", "placeholder", "name", "value", true, 2, 3), TextareaStrategy()))
}