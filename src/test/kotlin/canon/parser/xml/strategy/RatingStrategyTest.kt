package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class RatingStrategyTest {

    @Test
    fun testParse() {
        val xml = "<rating><block id='testId'><bold id='a' class='b'>text</bold></block></rating>"

        val parsed = RatingStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals(1, parsed.renderables!!.size)
        assertEquals("block", parsed.renderables!![0].getType())
    }
}