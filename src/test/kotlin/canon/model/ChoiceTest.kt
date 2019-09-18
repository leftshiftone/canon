package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ChoiceTest {

    @Test
    fun testChoiceMapping() {
        val mapped = Choice("testId", "testClass", "testText", "false",
                ArrayList()).toMap(HashMap())

        assertEquals(2, mapped.size)
        assertEquals("testText", mapped.get("text"))
        assertEquals("false", mapped.get("selected"))
    }
}
