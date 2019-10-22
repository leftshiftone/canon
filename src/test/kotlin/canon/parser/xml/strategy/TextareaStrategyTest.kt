package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class TextareaStrategyTest {

    @Test
    fun testParse() {
        val xml = "<textarea id='testId' class='testClass' placeholder='*' " +
                "name='testName' value='testValue' rows='5' cols='3'></textarea>"
        val parsed = TextareaStrategy().parse(xml.toNode(), HashMap<String, Any?>(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("*", parsed.placeholder)
        assertEquals("testName", parsed.name)
        assertEquals("testValue", parsed.value)
        assertEquals(false, parsed.required)
        assertEquals(5, parsed.rows)
        assertEquals(3, parsed.cols)
    }

}
