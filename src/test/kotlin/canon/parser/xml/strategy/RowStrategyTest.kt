package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class RowStrategyTest {

    @Test
    fun testParse() {
        val xml = "<row id='testId' class='testClass'>" +
                "<text id='testId2' class='testClass'>test</text>" +
                "</row>"
        val parsed = RowStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals(1, parsed.renderables?.size)
    }

}
