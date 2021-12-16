package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ImageTest {

    @Test
    fun testImageMapping() {
        val mapped = Image("testId", "testClass", "testAriaLabel", "testSrc",
                "100", "100", "testAlt").toMap(HashMap(), TestEvaluator())

        assertEquals(7, mapped.size)
        assertEquals("testSrc", mapped["src"])
        assertEquals("100", mapped["width"])
        assertEquals("100", mapped["height"])
        assertEquals("testAlt", mapped["alt"])
    }
}
