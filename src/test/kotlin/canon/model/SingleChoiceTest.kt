package canon.model

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SingleChoiceTest {

    @Test
    fun testSingleChoiceMapping() {
        val mapped = SingleChoice("testId", "testClass", "testName", false,
                ArrayList()).toMap(HashMap(), mockk())

        assertEquals(2, mapped.size)
        assertEquals("testName", mapped.get("name"))
        assertEquals(false, mapped.get("sieve"))
    }
}
