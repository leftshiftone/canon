package canon.parser.map.strategy

import canon.model.Headline
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HeadlineStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Headline("id", "class", "text"), HeadlineStrategy()))
}