package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LinkStrategyTest {

    @Test
    fun testParse() {
        val xml = "<link id='testId' class='testClass' value='testValue'>42 is the answer</link>"
        val parsed = LinkStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertThat(parsed).isNotNull()
        assertThat(parsed.id).isEqualTo("testId")
        assertThat(parsed.`class`).isEqualTo("testClass")
        assertThat(parsed.value).isEqualTo("testValue")
        assertThat(parsed.text).isEqualTo("42 is the answer")
    }
}
