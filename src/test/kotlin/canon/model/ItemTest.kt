package canon.model

import canon.api.IRenderable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ItemTest {

    @Test
    fun testItemMapping() {
        val mapped = Item("testId", "testClass", ArrayList<IRenderable>()).toMap(HashMap<String, Any>())

        assertEquals(0, mapped.size)
    }
}