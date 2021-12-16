package canon.parser.map.strategy

import canon.model.SlotMachine
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SlotMachineStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(SlotMachine("id", "class", "ariaLabel", "name", emptyList()), SlotMachineStrategy()))
}