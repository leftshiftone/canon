package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SuggestionTest {

    @Test
    fun testSuggestionMapping() {
        val mapped = Suggestion("testId", "testClass", "testText", "testName",
                "testValue").toMap(HashMap<String, Any>())

        assertEquals(3, mapped.size)
        assertEquals("testText", mapped.get("text"))
        assertEquals("testName", mapped.get("name"))
        assertEquals("testValue", mapped.get("value"))
    }
}