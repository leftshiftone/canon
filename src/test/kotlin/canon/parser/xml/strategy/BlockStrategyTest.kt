package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class BlockStrategyTest {
    
    @Test
    fun testParse() {
        val xml = "<block id='testId' class='testClass' name='testBlock'><bold id='a' class='b'>text</bold></block>"

        val parsed = BlockStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)
        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testBlock", parsed.name)
    }
}