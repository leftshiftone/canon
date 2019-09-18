package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class ButtonStrategyTest {

    @Test
    fun testParse() {
        val xml = "<button id='testId' class='testClass' text='testText' name='testName' value='testValue'></button>"
        val parsed = ButtonStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testText", parsed.text)
        assertEquals("testName", parsed.name)
        assertEquals("testValue", parsed.value)

    }

}
