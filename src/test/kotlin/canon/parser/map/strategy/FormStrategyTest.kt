package canon.parser.map.strategy

import canon.model.Form
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FormStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Form("id", "class", "name", emptyList()), FormStrategy()))
}