package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TextTest {

    @Test
    fun testText() {
        val mapped = Text("testId", "testClass", "{{text}}").toMap(mapOf(), TestEvaluator())

        assertEquals(3, mapped.size)
        assertEquals("testId", mapped["id"])
        assertEquals("testClass", mapped["class"])
        assertEquals("text", mapped["text"])
    }
}
