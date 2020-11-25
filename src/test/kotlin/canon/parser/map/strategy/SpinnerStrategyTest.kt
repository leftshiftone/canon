package canon.parser.map.strategy

import canon.model.Spinner
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SpinnerStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Spinner("id", "class", 1.0, 10.0, 1.0, 5.0, "name"), SpinnerStrategy()))
}