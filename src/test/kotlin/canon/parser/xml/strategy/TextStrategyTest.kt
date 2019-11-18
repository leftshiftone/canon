package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class TextStrategyTest {

    @Test
    fun testParse() {
        val xml = "<text id='testId' class='testClass' regex='regEx' placeholder='*' " +
                "required='true' name='testName'>test</text>"
        val parsed = TextStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("regEx", parsed.regex)
        assertEquals("*", parsed.placeholder)
        assertEquals(true, parsed.required)
        assertEquals("testName", parsed.name)
        assertEquals("test", parsed.value)
    }

}
