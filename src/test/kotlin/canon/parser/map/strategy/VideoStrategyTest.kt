package canon.parser.map.strategy

import canon.model.Video
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class VideoStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Video("id", "class", "src"), VideoStrategy()))
}