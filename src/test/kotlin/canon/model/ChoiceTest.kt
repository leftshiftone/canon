package canon.model

import canon.api.IRenderable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ChoiceTest {

    @Test
    fun testChoiceMapping() {
        val mapped = Choice("testId", "testClass", "testText", "false",
                ArrayList<IRenderable>()).toMap(HashMap<String, Any>())

        assertEquals(2, mapped.size)
        assertEquals("testText", mapped.get("text"))
        assertEquals("false", mapped.get("selected"))
    }
}