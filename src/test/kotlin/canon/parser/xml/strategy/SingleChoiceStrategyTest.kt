package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class SingleChoiceStrategyTest {

    @Test
    fun testParse() {
        val xml = "<singleChoice id='testId' class='testClass' name='testName' sieve='true'>" +
                "<choice id='testId2' class='testClass' text='testText' selected='true'></choice>" +
                "</singleChoice>"
        val parsed = SingleChoiceStrategy().parse(xml.toNode(), HashMap<String, Any?>(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testName", parsed.name)
        assertEquals(true, parsed.sieve)
        assertEquals(1, parsed.renderables?.size)
    }

}
