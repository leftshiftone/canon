package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import canon.support.Base64
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class ButtonStrategyTest {

    @Test
    fun testParse1() {
        val xml = "<button id='testId' class='testClass' text='testText' name='testName' value='testValue'>testText</button>"
        val parsed = ButtonStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testName", parsed.name)
        assertEquals("testText", parsed.text)
        assertEquals("{\"payload\":\"testValue\"}", Base64.decode(parsed.value).toString())
    }

    @Test
    fun testParse2() {
        val xml = "<button id='testId' class='testClass' text='testText' name='testName' value='testValue'><!--- test -->testText</button>"
        val parsed = ButtonStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testName", parsed.name)
        assertEquals("testText", parsed.text)
        assertEquals("{\"payload\":\"testValue\"}", Base64.decode(parsed.value).toString())
    }

    @Test
    fun testParse3() {
        val xml = "<button id='testId' class='testClass' text='testText' name='testName' value='testValue'>\n\n<label>testText</label></button>"
        val parsed = ButtonStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testName", parsed.name)
        assertEquals(null, parsed.text)
        assertEquals("{\"payload\":\"testValue\"}", Base64.decode(parsed.value).toString())
    }

}
