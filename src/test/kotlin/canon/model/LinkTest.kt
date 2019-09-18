package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class LinkTest {

    @Test
    fun testLinkMapping() {
        val mapped = Link("testId", "testClass", "testValue", "testText").toMap(HashMap())

        assertEquals(2, mapped.size)
        assertEquals("testValue", mapped.get("value"))
        assertEquals("testText", mapped.get("text"))
    }
}
