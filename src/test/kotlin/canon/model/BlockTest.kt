package canon.model

import canon.api.IRenderable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BlockTest {

    @Test
    fun testBlockMapping() {
        val block = Block("testId", "block", "testName", ArrayList<IRenderable>())

        val mapped = block.toMap(HashMap<String, Any>())
        assertEquals(1, mapped.size)
        assertEquals("testName", mapped.get("name"))
    }
}