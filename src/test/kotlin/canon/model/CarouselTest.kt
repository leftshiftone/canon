package canon.model

import canon.api.IRenderable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class CarouselTest {

    @Test
    fun testCarouselMapping() {
        val mapped = Carousel("testId", "testClass", "testText", "testName",
                false, ArrayList<IRenderable>()).toMap(HashMap<String, Any>())

        assertEquals(3, mapped.size)
        assertEquals("testText", mapped.get("text"))
        assertEquals("testName", mapped.get("name"))
        assertEquals(false, mapped.get("selected"))
    }
}