package canon.parser.xml.strategy

import canon.extension.toNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class EmailStrategyTest {

    @Test
    fun testParse() {
        val xml = "<email id='a' class='b' placeholder='c' required='true' name='d'>text</email>"
        val tag = EmailStrategy().parse(xml.toNode())

        Assertions.assertEquals(tag.id, "a")
        Assertions.assertEquals(tag.`class`, "b")
        Assertions.assertEquals(tag.placeholder, "c")
        Assertions.assertEquals(tag.required, true)
        Assertions.assertEquals(tag.name, "d")
        Assertions.assertEquals(tag.value, "text")
    }

}
