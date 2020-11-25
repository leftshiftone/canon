package canon.parser.map.strategy

import canon.model.Table
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TableStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Table("id", "class", "name", emptyList()), TableStrategy()))
}