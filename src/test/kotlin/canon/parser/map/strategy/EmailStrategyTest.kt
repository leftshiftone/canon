package canon.parser.map.strategy

import canon.model.Email
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class EmailStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Email("id", "class", "ariaLabel", "placeholder", true, "name", "value"), EmailStrategy()))
}