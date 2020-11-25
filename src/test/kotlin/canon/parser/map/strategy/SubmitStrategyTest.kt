package canon.parser.map.strategy

import canon.model.Submit
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SubmitStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Submit("id", "class", "text", "name"), SubmitStrategy()))
}