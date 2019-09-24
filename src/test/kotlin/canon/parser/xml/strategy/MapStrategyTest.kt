package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class MapStrategyTest {

    @Test
    fun testParse() {
        val xml = "<map id='testId' class='testClass' name='testName' src='testSrc' " +
                "mapType='type' centerLng='12.0' centerLat='10.0' exact='true'></map>"
        val parsed = MapStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testName", parsed.name)
        assertEquals("testSrc", parsed.src)
        assertEquals("type", parsed.mapType)
        assertEquals("12.0", parsed.centerLng)
        assertEquals("10.0", parsed.centerLat)
        assertEquals(true, parsed.exact)
        assertEquals(false, parsed.centerBrowserLocation)
        assertEquals(false, parsed.required)

    }

}
