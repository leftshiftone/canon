package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class TransitionStrategyTest {

    @Test
    fun testParse() {
        val xml = "<transition id='testId' class='testClass' name='testName' direction='right' wrapped='in'>" +
                "<text id='testId2' class='testClass'>test</text>" +
                "</transition>"
        val parsed = TransitionStrategy().parse(xml.toNode(), HashMap<String, Any?>(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testName", parsed.name)
        assertEquals("right", parsed.direction)
        assertEquals("in", parsed.wrapped)
        assertEquals(1, parsed.renderables?.size)
    }

}
