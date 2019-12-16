package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MultipleChoiceStrategyTest {

    @Test
    fun `ordinary parsing`() {
        val xml = "<multipleChoice id='testId' class='testClass' name='testName' sieve='true'>" +
                "<choice id='testId2' class='testClass' text='testText' selected='true'></choice>" +
                "<choice id='testId3' class='testClass' text='testText' selected='false'></choice>" +
                "</multipleChoice>"
        val parsed = MultipleChoiceStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertThat(parsed).isNotNull
        assertThat(parsed.id).isEqualTo("testId")
        assertThat(parsed.`class`).isEqualTo("testClass")
        assertThat(parsed.name).isEqualTo("testName")
        assertThat(parsed.sieve).isEqualTo(true)
        assertThat(parsed.renderables).hasSize(2)
    }

    @Test
    fun `ordinary parsing with default value for sieve is true`() {
        val xml = "<multipleChoice id='testId' class='testClass' name='testName'>" +
                "<choice id='testId2' class='testClass' text='testText' selected='true'></choice>" +
                "<choice id='testId3' class='testClass' text='testText' selected='false'></choice>" +
                "</multipleChoice>"
        val parsed = MultipleChoiceStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertThat(parsed).isNotNull
        assertThat(parsed.id).isEqualTo("testId")
        assertThat(parsed.`class`).isEqualTo("testClass")
        assertThat(parsed.name).isEqualTo("testName")
        assertThat(parsed.sieve).isEqualTo(true)
        assertThat(parsed.renderables).hasSize(2)
    }
}
