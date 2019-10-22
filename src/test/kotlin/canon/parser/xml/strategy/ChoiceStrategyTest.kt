package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class ChoiceStrategyTest {

    @Test
    fun testParse() {
        val xml = "<choice id='testId' class='testClass' text='testText' selected='true'></choice>"
        val parsed = ChoiceStrategy().parse(xml.toNode(), HashMap<String, Any?>(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testText", parsed.text)
        assertEquals(true, parsed.selected)
    }

}
