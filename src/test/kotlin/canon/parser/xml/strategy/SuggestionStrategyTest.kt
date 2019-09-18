package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class SuggestionStrategyTest {

    @Test
    fun testParse() {
        val xml = "<suggestion id='testId' class='testClass' name='testName' value='testValue'>text</suggestion>"
        val parsed = SuggestionStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("text", parsed.text)
        assertEquals("testName", parsed.name)
        assertEquals("testValue", parsed.value)
    }

}
