package canon.parser.map.strategy

import canon.model.Transition
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TransitionStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Transition("id", "class", "name", "direction", "wrapped", emptyList()), TransitionStrategy()))
}