package canon.model

import canon.api.IRenderable
import canon.support.TestEvaluator
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat


class ChoiceTest {

    @Test
    fun testChoiceMapping() {
        val mapped = Choice("testId", "testClass", "choiceName","testText", "false",
                ArrayList()).toMap(HashMap(), TestEvaluator())

        assertThat(mapped.size).isEqualTo(5)
        assertThat(mapped.get("text")).isEqualTo("testText")
        assertThat(mapped.get("selected")).isEqualTo(false)
    }

    @Test
    fun testChoiceMappingWithLabel() {
        val mapped = Choice(id = "testId", `class` = "testClass", name ="choiceName", selected = "false", text = null,
                renderables = listOf<IRenderable>(Label(id = "labelId",`class` = "LabelClass", text = "labelText")))
                .toMap(HashMap(), TestEvaluator())

        assertThat(mapped.size).isEqualTo(4)
        assertThat(mapped.get("text")).isNull()
        assertThat(mapped.get("selected")).isEqualTo(false)
    }
}
