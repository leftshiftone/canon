package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class FormTest {

    @Test
    fun testFormMapping() {
        val mapped = Form("testId", "testClass", "testName", ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(3, mapped.size)
        assertEquals("testName", mapped.get("name"))
    }
}
