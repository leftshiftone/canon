package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SpinnerTest {

    @Test
    fun testSpinnerMapping() {
        val mapped = Spinner("testId", "testClass", "testAriaLabel", 1.0, 101.0, 1.0, 25.0,
                "testName").toMap(HashMap(), TestEvaluator())

        assertEquals(8, mapped.size)
        assertEquals(1.0, mapped["min"])
        assertEquals(101.0, mapped["max"])
        assertEquals(1.0, mapped["step"])
        assertEquals(25.0, mapped["value"])
        assertEquals("testName", mapped["name"])
    }
}
