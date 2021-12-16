package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SlotMachineTest {

    @Test
    fun testSlotMachineMapping() {
        val mapped = SlotMachine("testId", "testClass", "testAriaLabel", "testName",
                ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(4, mapped.size)
        assertEquals("testName", mapped["name"])
    }
}
