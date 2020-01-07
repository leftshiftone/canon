package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class UploadStrategyTest {

    @Test
    fun testParse() {
        val xml = "<upload id='testId' class='testClass' accept='jpeg, png' name='testName' " +
                "maxSize='5.0' maxCompressSize='1.0'>test</upload>"
        val parsed = UploadStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertThat(parsed).isNotNull()
        assertThat(parsed.id).isEqualTo("testId")
        assertThat(parsed.`class`).isEqualTo("testClass")
        assertThat(parsed.accept).isEqualTo("jpeg, png")
        assertThat(parsed.name).isEqualTo("testName")
        assertThat(parsed.maxSize).isEqualTo(5.0)
        assertThat(parsed.maxCompressSize).isEqualTo(1.0)
    }

}
