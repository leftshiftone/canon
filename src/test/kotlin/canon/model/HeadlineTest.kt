package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class HeadlineTest {

    @Test
    fun testHeadlineMapping() {
        val mapped = Headline("testId", "testClass", "testText").toMap(HashMap())

        assertEquals(1, mapped.size)
        assertEquals("testText", mapped.get("text"))
    }
}
