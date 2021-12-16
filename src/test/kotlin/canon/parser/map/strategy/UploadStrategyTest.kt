package canon.parser.map.strategy

import canon.model.Upload
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class UploadStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Upload("id", "class", "ariaLabel", "accept", "name", "text", 10.0, 10.0, null), UploadStrategy()))
}