package canon.parser.map.strategy

import canon.model.Carousel
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CarouselStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Carousel("id", "class", "text", "name", true, emptyList()), CarouselStrategy()))
}