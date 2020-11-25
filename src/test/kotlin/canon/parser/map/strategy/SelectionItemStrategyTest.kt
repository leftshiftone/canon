package canon.parser.map.strategy

import canon.model.SelectionItem
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SelectionItemStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(SelectionItem("id", "class", "name", emptyList()), SelectionItemStrategy()))
}