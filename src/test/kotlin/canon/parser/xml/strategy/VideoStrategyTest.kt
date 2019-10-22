package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class VideoStrategyTest {

    @Test
    fun testParse() {
        val xml = "<video id='testId' class='testClass' src='testSrc'>test</video>"
        val parsed = VideoStrategy().parse(xml.toNode(), HashMap<String, Any?>(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testSrc", parsed.src)
    }

}
