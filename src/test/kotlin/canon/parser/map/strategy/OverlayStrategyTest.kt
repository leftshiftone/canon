package canon.parser.map.strategy

import canon.model.Overlay
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class OverlayStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Overlay("id", "class", "trigger", emptyList()), OverlayStrategy()))
}