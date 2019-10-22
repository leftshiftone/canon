package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class EmailStrategyTest {

    @Test
    fun testParse() {
        val xml = "<email id='testId' class='testClass' placeholder='*' required='true' name='testName'>text</email>"
        val parsed = EmailStrategy().parse(xml.toNode(), HashMap<String, Any?>(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("*", parsed.placeholder)
        assertEquals(true, parsed.required)
        assertEquals("testName", parsed.name)
        assertEquals("text", parsed.value)
    }

}
