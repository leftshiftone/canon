package canon.model

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ItemTest {

    @Test
    fun testItemMapping() {
        val mapped = Item("testId", "testClass", ArrayList()).toMap(HashMap(), mockk())

        assertEquals(0, mapped.size)
    }
}
