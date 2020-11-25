package canon.parser.map.strategy

import canon.model.Phone
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PhoneStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Phone("id", "class", "placeholder", true, "name", "value"), PhoneStrategy()))
}