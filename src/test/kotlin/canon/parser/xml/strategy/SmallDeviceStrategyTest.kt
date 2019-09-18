package canon.parser.xml.strategy

import canon.extension.toNode
import canon.model.Camera
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class SmallDeviceStrategyTest {

    @Test
    fun testParse() {
        val xml = "<smallDevice id='testId' class='testClass' name='testName'>" +
                "<text id='testId2' class='testClass'>test</text>" +
                "</smallDevice>"
        val parsed = SmallDeviceStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testName", parsed.name)
        assertEquals(1, parsed.renderables.size)
    }

}
