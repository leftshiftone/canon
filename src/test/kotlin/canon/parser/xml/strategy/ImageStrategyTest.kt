package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class ImageStrategyTest {

    @Test
    fun testParse() {
        val xml = "<image id='testId' class='testClass' src='testSrc' width='10.0' height='20.0'></image>"
        val parsed = ImageStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testSrc", parsed.src)
        assertEquals("10.0", parsed.width)
        assertEquals("20.0", parsed.height)

    }

}
