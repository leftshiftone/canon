package canon.model

import canon.api.IRenderable
import canon.support.TestEvaluator
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat


class ChoiceTest {

    @Test
    fun testChoiceMapping() {
        val mapped = Choice("testId", "testClass", "testAriaLabel", "choiceName","testText", "false",
                ArrayList()).toMap(HashMap(), TestEvaluator())

        assertThat(mapped.size).isEqualTo(6)
        assertThat(mapped["text"]).isEqualTo("testText")
        assertThat(mapped["selected"]).isEqualTo(false)
    }

    @Test
    fun testChoiceMappingWithLabel() {
        val mapped = Choice(id = "testId", `class` = "testClass", ariaLabel = "testAriaLabel", name ="choiceName", selected = "false", text = null,
                renderables = listOf<IRenderable>(Label(id = "labelId",`class` = "LabelClass", ariaLabel = "testAriaLabel", text = "labelText")))
                .toMap(HashMap(), TestEvaluator())

        assertThat(mapped.size).isEqualTo(5)
        assertThat(mapped["text"]).isNull()
        assertThat(mapped["selected"]).isEqualTo(false)
    }

    @Test
    fun testChoiceMappingWithOtherElements() {
        val mapped = Choice(id = "testId", `class` = "testClass", ariaLabel = "testAriaLabel", name ="choiceName", selected = "false", text = null,
                renderables = listOf<IRenderable>(
                        Bold(id = null, `class` = null, ariaLabel = null, text = "boldText"),
                        Italic(id = null, `class` = null, ariaLabel = null, text = "italicText"),
                        Block(id = null, `class` = null, ariaLabel = null, name = "block", renderables = arrayListOf())))
                .toMap(HashMap(), TestEvaluator())

        assertThat(mapped.size).isEqualTo(5)
        assertThat(mapped["text"]).isNull()
        assertThat(mapped["selected"]).isEqualTo(false)

    }
}
