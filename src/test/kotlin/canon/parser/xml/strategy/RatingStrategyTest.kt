package canon.parser.xml.strategy

import canon.extension.toNode
import canon.model.Rating
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class RatingStrategyTest {

    @Test
    fun testParse() {
        val xml = "<rating><block id='testId'><bold id='a' class='b'>text</bold></block></rating>"

        val parsed : Rating = RatingStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("true", parsed.enabled)
        assertEquals(1, parsed.renderables!!.size)
        assertEquals("block", parsed.renderables!![0].getType())
    }
}