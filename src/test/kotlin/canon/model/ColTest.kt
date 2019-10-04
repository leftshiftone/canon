package canon.model

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ColTest {

    @Test
    fun testColMapping() {
        val mapped = Col("testId", "testClass", ArrayList()).toMap(HashMap(), mockk())

        assertEquals(0, mapped.size)
    }
}
