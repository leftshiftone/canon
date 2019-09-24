package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ItemsTest {

    @Test
    fun testItemsMapping() {
        val mapped = Items("testId", "testClass", true, ArrayList()).toMap(HashMap())

        assertEquals(1, mapped.size)
        assertEquals(true, mapped.get("ordered"))
    }
}
