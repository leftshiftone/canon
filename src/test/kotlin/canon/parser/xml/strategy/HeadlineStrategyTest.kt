package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class HeadlineStrategyTest {

    @Test
    fun testParse() {
        val xml = "<headline id='testId' class='testClass' level='1'>text</headline>"
        val parsed = HeadlineStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("text", parsed.text)
        assertEquals("1", parsed.level)
    }

}
