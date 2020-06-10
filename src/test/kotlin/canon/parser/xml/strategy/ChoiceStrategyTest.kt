package canon.parser.xml.strategy

import canon.extension.toNode
import canon.model.Block
import canon.model.Bold
import canon.model.Italic
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
    fun testParseInvalidCombination() {
        val xml = "<choice id='testId' class='testClass' name='choiceName' selected='true'>testText " +
                "<label>label text</label></choice>"
        val parsed = ChoiceStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertThat(parsed).isNotNull
        assertThat(parsed.id).isEqualTo("testId")
        assertThat(parsed.`class`).isEqualTo("testClass")
        assertThat(parsed.name).isEqualTo("choiceName")
        assertThat(parsed.text).isNull()
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
        val label = parsed.renderables!![0] as Label
        assertThat(label.text).isEqualTo("testText")
        assertThat(label.`class`).isEqualTo("labelClass")
        assertThat(parsed.name).isEqualTo("choiceName")
        assertThat(parsed.selected).isEqualTo("true")
    }

    @Test
    fun testParseWithDifferentElement() {
        val xml = "<choice id='testId' class='testClass' name='choiceName' selected='true'>" +
                "<block name='blockName'></block>" +
                "<italic>italic text</italic>" +
                "<bold>bold text</bold>" +
                "</choice>"
        val parsed = ChoiceStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertThat(parsed).isNotNull
        assertThat(parsed.id).isEqualTo("testId")
        assertThat(parsed.`class`).isEqualTo("testClass")
        assertThat(parsed.text).isNull()

        val block = parsed.renderables!![0] as Block
        assertThat(block.name).isEqualTo("blockName")

        val italic = parsed.renderables!![1] as Italic
        assertThat(italic.text).isEqualTo("italic text")

        val bold = parsed.renderables!![2] as Bold
        assertThat(bold.text).isEqualTo("bold text")
    }

}
