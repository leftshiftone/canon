package canon.parser.xml.strategy

import canon.extension.toNode
import canon.model.Camera
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class CameraStrategyTest {

    @Test
    fun testParse() {
        val xml = "<camera id='testId' class='testClass' name='testName' maxCompressSize='1.0'></camera>"
        val parsed = CameraStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("testName", parsed.name)
        assertEquals(false, parsed.required)
        assertEquals(1.0, parsed.maxCompressSize)
    }

}
