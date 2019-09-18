package canon.parser.xml.strategy

import canon.extension.toNode
import canon.model.Camera
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class CodeReaderStrategyTest {

    @Test
    fun testParse() {
        val xml = "<codeReader id='testId' class='testClass' name='testName' format='format'></codeReader>"
        val parsed = CodeReaderStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testName", parsed.name)
        assertEquals("format", parsed.format)
    }

}
