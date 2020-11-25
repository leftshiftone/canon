package canon.parser.map.strategy

import canon.model.Row
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RowStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Row("id", "class", emptyList()), RowStrategy()))
}