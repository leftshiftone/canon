package canon.parser.map.strategy

import canon.model.Link
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class LinkStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Link("id", "class", "ariaLabel", "value", "text"), LinkStrategy()))
}