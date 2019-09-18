package canon.parser.xml.strategy

import canon.extension.toNode
import canon.model.Camera
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class UploadStrategyTest {

    @Test
    fun testParse() {
        val xml = "<upload id='testId' class='testClass' accept='jpeg, png' name='testName' " +
                "maxSize='5.0' maxCompressSize='1.0'>test</upload>"
        val parsed = UploadStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertNotNull(parsed)
        assertEquals("testId", parsed.id)
        assertEquals("testClass", parsed.`class`)
        assertEquals("jpeg, png", parsed.accept)
        assertEquals("testName", parsed.name)
        assertEquals(5.0, parsed.maxSize)
        assertEquals(1.0, parsed.maxCompressionSize)
    }

}
