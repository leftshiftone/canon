package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class ItemStrategyTest {
    
    @Test
    fun testParse() {
        val xml = "<item id='testId' class='testClass' ordered='false'><item id='a' class='b'></item></item>"
        val parsed = ItemStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals(1, parsed.renderables?.size)

    }
}