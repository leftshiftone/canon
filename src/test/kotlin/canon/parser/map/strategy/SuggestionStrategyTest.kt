package canon.parser.map.strategy

import canon.model.Suggestion
import canon.support.Base64
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SuggestionStrategyTest {

    @Test
    fun parseTest() = assertTrue(testStrategy(Suggestion("id", "class", "text", "name", Base64.encodeUTF8String("value")), SuggestionStrategy()))
}