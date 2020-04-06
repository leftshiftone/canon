package canon.model

import canon.support.Base64
import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ButtonTest {

    @Test
    fun testButtonMapping() {
        val mapped = Button("testId", "testClass",
                "testName", Base64.encode(mapOf("payload" to "testValue")), ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(4, mapped.size)
        assertEquals("testName", mapped.get("name"))
        assertEquals("eyJwYXlsb2FkIjoidGVzdFZhbHVlIn0=", mapped.get("value"))
    }
}
