package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class CarouselStrategyTest {

    @Test
    fun testParse() {
        val xml = "<carousel id='testId' class='testClass' text='testText' name='testName'></carousel>"
        val parsed = CarouselStrategy().parse(xml.toNode(), HashMap<String, Any?>(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testText", parsed.text)
        assertEquals("testName", parsed.name)
        assertEquals(false, parsed.selected)
    }

}
