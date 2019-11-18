package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class SpinnerStrategyTest {

    @Test
    fun testParse() {
        val xml = "<spinner id='testId' class='testClass' name='testName' " +
                "min='1.0' step='2.0' value='1.5' values='1.0, 5.0, 10.0'>" +
                "</spinner>"
        val parsed = SpinnerStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testName", parsed.name)
        assertEquals(1.0, parsed.min)
        assertEquals(10.0, parsed.max)
        assertEquals(2.0, parsed.step)
        assertEquals(1.5, parsed.value)
    }

}
