package canon.parser.map.strategy

import canon.model.CodeReader
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CodeReaderStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(CodeReader("id", "class", "ariaLabel", "name", "format"), CodeReaderStrategy()))
}