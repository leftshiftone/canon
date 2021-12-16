package canon.model

import canon.support.TestEvaluator
import canon.visitor.GaiaVisitor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class IfTest {

    @Test
    fun `test if with true`() {
        val renderable = If("{{true}}", Label(null, null, null,"label"))

        val mapped = renderable.accept(GaiaVisitor(mapOf(), TestEvaluator()), TestEvaluator())
        assertEquals(1, mapped.size)
        assertEquals(mapped[0]["text"], "label")
    }

    @Test
    fun `test if with false`() {
        val renderable = If("{{false}}", Label(null, null, null,"label"))

        val mapped = renderable.accept(GaiaVisitor(mapOf(), TestEvaluator()), TestEvaluator())
        assertEquals(0, mapped.size)
    }

}
