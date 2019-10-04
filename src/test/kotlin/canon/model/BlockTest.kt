package canon.model

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BlockTest {

    @Test
    fun testBlockMapping() {
        val block = Block("testId", "block", "testName", ArrayList())

        val mapped = block.toMap(HashMap(), mockk())
        assertEquals(1, mapped.size)
        assertEquals("testName", mapped.get("name"))
    }
}
