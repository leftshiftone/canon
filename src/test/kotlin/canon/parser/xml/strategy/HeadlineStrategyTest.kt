package canon.parser.xml.strategy

import canon.extension.toNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class HeadlineStrategyTest {

    @Test
    fun testParse() {
        val xml = "<headline id='a' class='b'>text</headline>"
        val tag = LabelStrategy().parse(xml.toNode())

        Assertions.assertEquals(tag.id, "a")
        Assertions.assertEquals(tag.`class`, "b")
        Assertions.assertEquals(tag.text, "text")
    }

}
