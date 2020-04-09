package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class ContainerStrategyTest {

    @Test
    fun testParse() {
        val xml = "<container id='testId' class='testClass' name='testName'>" +
                "<label id='testId2' class='testClass'>test</label>" +
                "</container>"
        val parsed = ContainerStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testName", parsed.name)
        assertEquals(1, parsed.renderables?.size)
    }
}
