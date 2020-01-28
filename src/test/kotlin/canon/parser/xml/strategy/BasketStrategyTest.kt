package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class BasketStrategyTest {

    @Test
    fun testParse() {
        val xml = "<basket id='testId' class='testClass' name='testBlock' required='true'><bold id='a' class='b'>text</bold></basket>"

        val parsed = BasketStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)
        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testBlock", parsed.name)
    }
}
