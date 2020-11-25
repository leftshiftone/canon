package canon.parser.map.strategy

import canon.model.Button
import canon.support.Base64
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ButtonStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Button("id", "class", "text", "name", Base64.encodeUTF8String("value"), emptyList()), ButtonStrategy()))
}