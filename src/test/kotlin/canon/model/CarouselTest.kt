package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class CarouselTest {

    @Test
    fun testCarouselMapping() {
        val mapped = Carousel("testId", "testClass", "testText", "testName",
                false, ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(5, mapped.size)
        assertEquals("testText", mapped.get("text"))
        assertEquals("testName", mapped.get("name"))
        assertEquals(false, mapped.get("selected"))
    }
}
