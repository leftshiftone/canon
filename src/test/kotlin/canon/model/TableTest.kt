package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TableTest {

    @Test
    fun testTableMapping() {
        val mapped = Table("testId", "testClass", "testName",
                ArrayList()).toMap(HashMap())

        assertEquals(1, mapped.size)
        assertEquals("testName", mapped.get("name"))
    }
}
