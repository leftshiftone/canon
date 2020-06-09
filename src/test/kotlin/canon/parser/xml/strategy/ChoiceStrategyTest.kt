package canon.parser.xml.strategy

import canon.extension.toNode
import canon.model.Label
import canon.parser.xml.CanonXmlParser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ChoiceStrategyTest {

    @Test
    fun testParse() {
        val xml = "<choice id='testId' class='testClass' name='choiceName' selected='true'>testText</choice>"
        val parsed = ChoiceStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertThat(parsed).isNotNull
        assertThat(parsed.id).isEqualTo("testId")
        assertThat(parsed.`class`).isEqualTo("testClass")
        assertThat(parsed.name).isEqualTo("choiceName")
        assertThat(parsed.text).isEqualTo("testText")
        assertThat(parsed.selected).isEqualTo("true")
    }


    @Test
    fun testParseWithChildren() {
        val xml = "<choice id='testId' class='testClass' name='choiceName' selected='true'>" +
                "<label class='labelClass'>testText</label>" +
                "<image src='http://test.image.com' /></choice>"
        val parsed = ChoiceStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertThat(parsed).isNotNull
        assertThat(parsed.id).isEqualTo("testId")
        assertThat(parsed.`class`).isEqualTo("testClass")
        assertThat(parsed.text).isNull()
        val label = parsed.renderables!!.get(0) as Label
        assertThat(label.text).isEqualTo("testText")
        assertThat(label.`class`).isEqualTo("labelClass")
        assertThat(parsed.name).isEqualTo("choiceName")
        assertThat(parsed.selected).isEqualTo("true")
    }

}
