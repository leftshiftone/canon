package canon.parser.xml.strategy

import canon.extension.toNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LabelStrategyTest {

    @Test
    fun testParse() {
        val xml = "<label id='a' class='b'>text</label>"
        val tag = LabelStrategy().parse(xml.toNode())

        Assertions.assertEquals(tag.id, "a")
        Assertions.assertEquals(tag.`class`, "b")
        Assertions.assertEquals(tag.text, "text")
    }

}
