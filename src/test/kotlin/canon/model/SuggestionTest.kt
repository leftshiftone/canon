package canon.model

import canon.support.Base64
import canon.support.TestEvaluator
import io.mockk.mockk
import org.apache.commons.lang3.StringUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SuggestionTest {

    @Test
    fun testSuggestionMapping() {
        val mapped = Suggestion("testId", "testClass", "testText", "testName",
                Base64.encode(mapOf("payload" to "testValue"))).toMap(HashMap(), TestEvaluator())
        
        assertEquals(3, mapped.size)
        assertEquals("testText", mapped.get("text"))
        assertEquals("testName", mapped.get("name"))
        assertEquals("eyJwYXlsb2FkIjoidGVzdFZhbHVlIn0=", mapped.get("value"))
    }
}
