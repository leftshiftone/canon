package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TextInputStrategyTest {

    @Test
    fun testParse() {
        val xml = "<textInput id='testId' class='testClass' regex='regEx' placeholder='*' " +
                "required='true' name='testName' value='test'></textInput>"
        val parsed = TextStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertThat(parsed).isNotNull
        assertThat(parsed.id).isEqualTo("testId")
        assertThat(parsed.`class`).isEqualTo("testClass")
        assertThat(parsed.regex).isEqualTo("regEx")
        assertThat(parsed.placeholder).isEqualTo("*")
        assertThat(parsed.required).isTrue()
        assertThat(parsed.name).isEqualTo("testName")
        assertThat(parsed.value).isEqualTo("test")
    }

}
