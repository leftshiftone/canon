package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class ItemsStrategyTest {
    
    @Test
    fun testParse() {
        val xml = "<items id='testId' class='testClass' ordered='false'><item id='a' class='b'></item></items>"
        val parsed = ItemsStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals(false, parsed.ordered)
        assertEquals(1, parsed.renderables?.size)

    }
}