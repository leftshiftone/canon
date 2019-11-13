package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class CodeReaderTest {

    @Test
    fun testCodeReaderMapping() {
        val mapped = CodeReader("testId", "testClass", "testName", "testFormat")
                .toMap(HashMap(), TestEvaluator())

        assertEquals(4, mapped.size)
        assertEquals("testName", mapped.get("name"))
        assertEquals("testFormat", mapped.get("format"))
    }
}
