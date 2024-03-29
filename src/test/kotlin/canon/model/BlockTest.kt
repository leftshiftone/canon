package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BlockTest {

    @Test
    fun testBlockMapping() {
        val block = Block("testId", "block", "testAriaLabel", "testName", ArrayList())

        val mapped = block.toMap(HashMap(), TestEvaluator())
        assertEquals(4, mapped.size)
        assertEquals("testName", mapped.get("name"))
    }
}
