package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ItemTest {

    @Test
    fun testItemMapping() {
        val mapped = Item("testId", "testClass", ArrayList()).toMap(HashMap())

        assertEquals(0, mapped.size)
    }
}
