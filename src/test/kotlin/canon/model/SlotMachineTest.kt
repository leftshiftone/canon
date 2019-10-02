package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SlotMachineTest {

    @Test
    fun testSlotMachineMapping() {
        val mapped = SlotMachine("testId", "testClass", "testName",
                ArrayList()).toMap(HashMap())

        assertEquals(1, mapped.size)
        assertEquals("testName", mapped.get("name"))
    }
}
