package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ImageTest {

    @Test
    fun testImageMapping() {
        val mapped = Image("testId", "testClass", "testSrc",
                "100", "100", "testAlt").toMap(HashMap())

        assertEquals(4, mapped.size)
        assertEquals("testSrc", mapped.get("src"))
        assertEquals("100", mapped.get("width"))
        assertEquals("100", mapped.get("height"))
        assertEquals("testAlt", mapped.get("alt"))
    }
}
