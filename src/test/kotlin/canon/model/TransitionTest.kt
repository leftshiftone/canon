package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TransitionTest {

    @Test
    fun testTransitionMapping() {
        val mapped = Transition("testId", "testClass", "testName","down", "right",
                ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(5, mapped.size)
        assertEquals("testName", mapped.get("name"))
        assertEquals("down", mapped.get("direction"))
        assertEquals("right", mapped.get("wrapped"))
    }
}
