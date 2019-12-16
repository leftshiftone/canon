package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class FormStrategyTest {

    @Test
    fun testParse() {
        val xml = "<form id='testId' class='testClass' name='submitForm'><submit>test</submit></form>"
        val parsed = FormStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("submitForm", parsed.name)
        assertEquals(1, parsed.renderables?.size)
    }

}
