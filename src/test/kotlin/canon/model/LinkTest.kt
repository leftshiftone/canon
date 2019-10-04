package canon.model

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class LinkTest {

    @Test
    fun testLinkMapping() {
        val mapped = Link("testId", "testClass", "testValue", "testText").toMap(HashMap(), mockk())

        assertEquals(2, mapped.size)
        assertEquals("testValue", mapped.get("value"))
        assertEquals("testText", mapped.get("text"))
    }
}
