package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class ColStrategyTest {

    @Test
    fun testParse() {
        val xml = "<col id='testId' class='testClass'><label>test</label></col>"
        val parsed = ColStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals(1, parsed.renderables?.size)
    }

}
