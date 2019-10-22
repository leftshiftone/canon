package canon.model

import canon.support.Base64
import canon.support.TestEvaluator
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ButtonTest {

    @Test
    fun testButtonMapping() {
        val mapped = Button("testId", "testClass", "testText",
                "testName", Base64.encode(mapOf("payload" to "testValue"))).toMap(HashMap(), TestEvaluator())

        assertEquals(3, mapped.size)
        assertEquals("testText", mapped.get("text"))
        assertEquals("testName", mapped.get("name"))
        assertEquals("eyJwYXlsb2FkIjoidGVzdFZhbHVlIn0=", mapped.get("value"))
    }
}
