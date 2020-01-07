package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat


class EmailStrategyTest {

    @Test
    fun testParse() {
        val xml = "<email id='testId' value='testValue' class='testClass' placeholder='*' required='true' name='testName'>text</email>"
        val parsed = EmailStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertThat(parsed).isNotNull()
        assertThat(parsed.id).isEqualTo("testId")
        assertThat(parsed.`class`).isEqualTo("testClass")
        assertThat(parsed.placeholder).isEqualTo("*")
        assertThat(parsed.required).isTrue()
        assertThat(parsed.name).isEqualTo("testName")
        assertThat(parsed.value).isEqualTo("testValue")
    }

}
