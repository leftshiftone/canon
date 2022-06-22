package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TransitionTest {

    @Test
    fun testTransitionMapping() {
        val mapped = Transition("testId", "testClass", "testAriaLabel", "testName","down", "right",
                ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(6, mapped.size)
        assertEquals("testName", mapped["name"])
        assertEquals("down", mapped["direction"])
        assertEquals("right", mapped["wrapped"])
    }
}
