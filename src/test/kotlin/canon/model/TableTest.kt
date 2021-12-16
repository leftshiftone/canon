package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TableTest {

    @Test
    fun testTableMapping() {
        val mapped = Table("testId", "testClass", "testAriaLabel", "testName",
                ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(4, mapped.size)
        assertEquals("testName", mapped["name"])
    }
}
