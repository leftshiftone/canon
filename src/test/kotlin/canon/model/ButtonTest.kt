package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ButtonTest {

    @Test
    fun testButtonMapping() {
        val mapped = Button("testId", "testClass", "testText",
                "testName", "testValue").toMap(HashMap())

        assertEquals(3, mapped.size)
        assertEquals("testText", mapped.get("text"))
        assertEquals("testName", mapped.get("name"))
        assertEquals("testValue", mapped.get("value"))
    }
}
