package canon.parser.map.strategy

import canon.model.Bold
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class BoldStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Bold("id", "class", "text"), BoldStrategy()))
}