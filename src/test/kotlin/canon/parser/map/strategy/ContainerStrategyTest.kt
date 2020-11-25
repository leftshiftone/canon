package canon.parser.map.strategy

import canon.model.Container
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ContainerStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Container("id", "class", "name", emptyList()), ContainerStrategy()))
}