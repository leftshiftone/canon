package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SingleChoiceStrategyTest {

    @Test
    fun testParse() {
        val xml = "<singleChoice id='testId' class='testClass' name='testName' sieve='true'>" +
                "<choice id='testId2' class='testClass' text='testText' selected='true'></choice>" +
                "</singleChoice>"
        val parsed = SingleChoiceStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertThat(parsed).isNotNull()
        assertThat(parsed.id).isEqualTo("testId")
        assertThat(parsed.`class`).isEqualTo("testClass")
        assertThat(parsed.name).isEqualTo("testName")
        assertThat(parsed.sieve).isEqualTo(true)
        assertThat(parsed.required).isEqualTo(false)
        assertThat(parsed.renderables?.size).isEqualTo(1)
    }

}
