package canon.model

import canon.api.IRenderable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class CodeReaderTest {

    @Test
    fun testCodeReaderMapping() {
        val mapped = CodeReader("testId", "testClass", "testName", "testFormat")
                .toMap(HashMap<String, Any>())

        assertEquals(2, mapped.size)
        assertEquals("testName", mapped.get("name"))
        assertEquals("testFormat", mapped.get("format"))
    }
}