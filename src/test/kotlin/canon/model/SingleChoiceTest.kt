package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SingleChoiceTest {

    @Test
    fun testSingleChoiceMapping() {
        val mapped = SingleChoice("testId", "testClass", "testName", false,
                ArrayList()).toMap(HashMap())

        assertEquals(2, mapped.size)
        assertEquals("testName", mapped.get("name"))
        assertEquals(false, mapped.get("sieve"))
    }
}
