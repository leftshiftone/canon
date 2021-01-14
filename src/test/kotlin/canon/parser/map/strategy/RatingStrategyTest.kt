package canon.parser.map.strategy

import canon.model.Rating
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RatingStrategyTest {

    @Test
    fun testParse() {
        assertTrue(testStrategy(Rating("true",emptyList()), RatingStrategy()))
    }
}