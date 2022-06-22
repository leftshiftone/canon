package canon.model

import canon.api.IEvaluator
import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class LinkTest {

    @Test
    fun testLinkMapping() {
        val mapped = Link("testId", "testClass", "testAriaLabel", "testValue", "testText").toMap(HashMap(), TestEvaluator())

        assertEquals(5, mapped.size)
        assertEquals("testValue", mapped["value"])
        assertEquals("testText", mapped["text"])
    }
}
