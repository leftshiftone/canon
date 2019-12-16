package canon.model

import canon.api.IEvaluator
import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class LinkTest {

    @Test
    fun testLinkMapping() {
        val mapped = Link("testId", "testClass", "testValue", "testText").toMap(HashMap(), TestEvaluator())

        assertEquals(4, mapped.size)
        assertEquals("testValue", mapped.get("value"))
        assertEquals("testText", mapped.get("text"))
    }
}
