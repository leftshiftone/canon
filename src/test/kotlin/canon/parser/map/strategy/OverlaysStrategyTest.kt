package canon.parser.map.strategy

import canon.model.Overlays
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class OverlaysStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Overlays("id", "class", "trigger", emptyList()), OverlaysStrategy()))
}