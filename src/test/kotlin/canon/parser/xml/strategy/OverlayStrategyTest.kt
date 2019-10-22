package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class OverlayStrategyTest {

    @Test
    fun testParse() {
        val xml = "<overlay id='testId' class='testClass' trigger='testTrigger'>" +
                "<text id='testId2' class='testClass'>test</text>" +
                "</overlay>"
        val parsed = OverlaysStrategy().parse(xml.toNode(), HashMap<String, Any?>(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testTrigger", parsed.trigger)
        assertEquals(1, parsed.renderables?.size)
    }

}
