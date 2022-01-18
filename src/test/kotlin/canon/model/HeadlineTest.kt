package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class HeadlineTest {

    @Test
    fun testHeadlineMapping() {
        val mapped = Headline("testId", "testClass", "testAriaLabel", "testText", "1").toMap(HashMap(), TestEvaluator())

        assertEquals(5, mapped.size)
        assertEquals("testText", mapped["text"])
        assertEquals("1", mapped["level"])
    }
}
