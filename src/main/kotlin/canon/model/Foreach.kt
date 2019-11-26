package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.IStackeable
import canon.api.IVisitor
import canon.support.Iterators
import canon.support.Maps
import com.fasterxml.jackson.annotation.JsonIgnore

data class Foreach(val forEachStmt: String?, val renderable: IRenderable?) : IRenderable, IStackeable {

    @JsonIgnore
    private val target: String
    @JsonIgnore
    private val source: String

    val renderables: List<IRenderable>

    init {
        this.target = forEachStmt?.let { getTarget(it) }.toString()
        this.source = forEachStmt?.let { getSource(it) }.toString()
        @Suppress("UNCHECKED_CAST")
        this.renderables = listOf(renderable) as List<IRenderable>
    }

    private fun getTarget(forEachStmt: String): String {
        val beforeInStmt = forEachStmt.substringBefore(" in ")

        assert(beforeInStmt.isNotEmpty()) { "the foreach target attribute must not be empty" }
        assert(beforeInStmt.trim().startsWith("$")) { "the foreach target attribute have to start with a '$' symbol" }
        return beforeInStmt.trim().substring(1)
    }

    private fun getSource(forEachStmt: String): String {
        val beforeInStmt = forEachStmt.substringAfter(" in ")

        assert(beforeInStmt.isNotEmpty()) { "the foreach target attribute must not be null" }
        assert(beforeInStmt.trim().startsWith("$")) { "the foreach target attribute have to start with a '$' symbol" }
        return beforeInStmt.trim().substring(1)
    }

    override fun accept(visitor: IVisitor, evaluator: IEvaluator) {
        val iterator = Iterators.toIterator(Maps.getDeep(visitor.getContext(), source))

        while (iterator.hasNext()) {
            val nested = visitor.getContext().toMutableMap()
            nested[target] = iterator.next()
            renderables.forEach(visitor.wrap(nested)::visitRenderable)
        }
        renderables.forEach(visitor::visitRenderable)
    }

    override fun getType(): String {
        return "text"
    }

    override fun toString() = "Foreach(target='$target', source='$source')"

}
