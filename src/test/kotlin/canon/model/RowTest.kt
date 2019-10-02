package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class RowTest {

    @Test
    fun testRowMapping() {
        val mapped = Row("testId", "testClass", ArrayList()).toMap(HashMap())

        assertEquals(0, mapped.size)
    }
}
