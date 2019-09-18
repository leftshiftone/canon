package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class HeadlineStrategyTest {

    @Test
    fun testParse() {
        val xml = "<headline id='a' class='b'>text</headline>"
       /* val tag = HeadlineStrategy().parse(xml.toNode(), CanonXmlParser::toRenderables)

        Assertions.assertEquals(tag.id, "a")
        Assertions.assertEquals(tag.`class`, "b")
        Assertions.assertEquals(tag.text, "text")*/
    }

}
