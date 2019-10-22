package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class ReelValueStrategyTest {

    @Test
    fun testParse() {
        val xml = "<reelValue id='testId' class='testClass' value='testValue' valueType='string'></reelValue>"
        val parsed = ReelValueStrategy().parse(xml.toNode(), HashMap<String, Any?>(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testValue", parsed.value)
        assertEquals("string", parsed.valueType)
    }

}
