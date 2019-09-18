package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ForeachTest {

    @Test
    fun testForeachMapping() {
        val mapped = Foreach("testId", "testClass", ArrayList()).toMap(HashMap())

        assertEquals(0, mapped.size)
    }
}
