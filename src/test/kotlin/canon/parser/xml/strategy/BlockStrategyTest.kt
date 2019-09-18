package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Test

class BlockStrategyTest {
    
    @Test
    fun testParse() {
        val xml = "<block id='a' class='b' name='testBlock'><bold id='a' class='b'>text</bold></block>"

        //val tag = BlockStrategy().parse(xml.toNode(), factory(xml.toNode()))
    }
}