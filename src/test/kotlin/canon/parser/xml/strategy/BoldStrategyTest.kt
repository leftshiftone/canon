package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BoldStrategyTest {

    @Test
    fun testParse() {
        val xml = "<bold id='a' class='b'>text</bold>"
        //val tag = BoldStrategy().parse(xml.toNode(), CanonXmlParser::toRenderables)

       /* Assertions.assertEquals(tag.id, "a")
        Assertions.assertEquals(tag.`class`, "b")
        Assertions.assertEquals(tag.text, "text")*/
    }

}
