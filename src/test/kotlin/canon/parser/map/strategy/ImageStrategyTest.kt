package canon.parser.map.strategy

import canon.model.Image
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ImageStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Image("id", "class", "ariaLabel", "src", "width", "height", "alt"), ImageStrategy()))
}