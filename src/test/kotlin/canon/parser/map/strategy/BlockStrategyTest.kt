package canon.parser.map.strategy

import canon.model.Block
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class BlockStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Block("id", "class", "name", emptyList()), BlockStrategy()))
}