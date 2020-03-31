package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class OverlaysStrategyTest {

    @Test
    fun testParse() {
        val xml = "<overlays id='testId' class='testClass' trigger='testTrigger'>" +
                "<overlay id='testId1' class='testClass' trigger='testTrigger'>" +
                "<label id='testId2' class='testClass'>test</label>" +
                "</overlay></overlays>"
        val parsed = OverlayStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testTrigger", parsed.trigger)
        assertEquals(1, parsed.renderables?.size)
    }

}
