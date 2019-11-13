package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SlotMachineTest {

    @Test
    fun testSlotMachineMapping() {
        val mapped = SlotMachine("testId", "testClass", "testName",
                ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(3, mapped.size)
        assertEquals("testName", mapped.get("name"))
    }
}
