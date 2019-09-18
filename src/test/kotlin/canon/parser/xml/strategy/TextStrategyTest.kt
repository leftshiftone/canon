package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TextStrategyTest {

    @Test
    fun testParse() {
        val xml = "<text id='a' class='b'>text</text>"
       /* val tag = TextStrategy().parse(xml.toNode(), CanonXmlParser::toRenderables)

        Assertions.assertEquals(tag.id, "a")
        Assertions.assertEquals(tag.`class`, "b")
        Assertions.assertEquals(tag.value, "text")*/
    }

}
