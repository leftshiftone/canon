package canon.model

import canon.api.IRenderable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ItemsTest {

    @Test
    fun testItemsMapping() {
        val mapped = Items("testId", "testClass", true, ArrayList<IRenderable>()).toMap(HashMap<String, Any>())

        assertEquals(1, mapped.size)
        assertEquals(true, mapped.get("ordered"))
    }
}