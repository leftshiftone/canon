package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class SelectionStrategyTest {

    @Test
    fun testParse() {
        val xml = "<selection id='testId' class='testClass' name='testName' countdownInSec='12'>" +
                "<items>" +
                "<item id='a' class='b'></item>" +
                "</items>" +
                "</selection>"
        val parsed = SelectionStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertThat(parsed).isNotNull
        assertThat(parsed.id).isEqualTo("testId")
        assertThat(parsed.`class`).isEqualTo("testClass")
        assertThat(parsed.name).isEqualTo("testName")
        assertThat(parsed.countdownInSec).isEqualTo(12)
        assertThat(parsed.renderables?.get(0)).isNotNull
        assertThat(parsed.renderables?.size).isEqualTo(1)
    }
}
