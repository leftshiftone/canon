package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ChoiceStrategyTest {

    @Test
    fun testParse() {
        val xml = "<choice id='testId' class='testClass' name='choiceName' text='testText' selected='true'></choice>"
        val parsed = ChoiceStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertThat(parsed).isNotNull
        assertThat(parsed.id).isEqualTo("testId")
        assertThat(parsed.`class`).isEqualTo("testClass")
        assertThat(parsed.name).isEqualTo("choiceName")
        assertThat(parsed.text).isEqualTo("testText")
        assertThat(parsed.selected).isEqualTo("true")
    }

}
