package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class HeadlineTest {

    @Test
    fun testHeadlineMapping() {
        val mapped = Headline("testId", "testClass", "testText").toMap(HashMap(), TestEvaluator())

        assertEquals(3, mapped.size)
        assertEquals("testText", mapped.get("text"))
    }
}
