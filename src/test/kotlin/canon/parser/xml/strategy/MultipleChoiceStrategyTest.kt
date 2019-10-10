package canon.parser.xml.strategy

import canon.extension.toNode
import canon.model.Camera
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class MultipleChoiceStrategyTest {

    @Test
    fun testParse() {
        val xml = "<multipleChoice id='testId' class='testClass' name='testName' sieve='true'>" +
                "<choice id='testId2' class='testClass' text='testText' selected='true'></choice>" +
                "<choice id='testId3' class='testClass' text='testText' selected='false'></choice>" +
                "</multipleChoice>"
        val parsed = MultipleChoiceStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testName", parsed.name)
        assertEquals(2, parsed.renderables?.size)
    }

}
