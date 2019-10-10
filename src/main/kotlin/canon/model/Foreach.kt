package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.IStackeable
import canon.api.IVisitor
import canon.support.Iterators
import canon.support.Maps
import com.fasterxml.jackson.annotation.JsonIgnore

class Foreach (forEachStmt: String?, renderables: IRenderable?) : IRenderable, IStackeable {

    @JsonIgnore
    private val target: String
    @JsonIgnore
    private val source: String
    override val renderables: List<IRenderable>

    init {
        this.target = forEachStmt?.let { getTarget(it) }.toString()
        this.source = forEachStmt?.let { getSource(it) }.toString()
        this.renderables = listOf(renderables) as List<IRenderable>
    }

    private fun getTarget(forEachStmt: String): String {
        val beforeInStmt = forEachStmt.substringBefore(" in ")

        assert(!beforeInStmt.isNullOrEmpty()) { "the foreach target attribute must not be null" }
        assert(beforeInStmt.trim().startsWith("$")) { "the foreach target attribute have to start with a '$' symbol" }
        return beforeInStmt.trim().substring(1)
    }

    private fun getSource(forEachStmt: String): String {
        val beforeInStmt = forEachStmt.substringAfter(" in ")

        assert(!beforeInStmt.isNullOrEmpty()) { "the foreach target attribute must not be null" }
        assert(beforeInStmt.trim().startsWith("$")) { "the foreach target attribute have to start with a '$' symbol" }
        return beforeInStmt.trim().substring(1)
    }

    override fun accept(visitor: IVisitor, evaluator: IEvaluator) {
        val iterator = Iterators.toIterator(Maps.getDeep(visitor.getContext(), source))

        while (iterator.hasNext()) {
            val nested = HashMap(visitor.getContext()).put(target, iterator.next())
            renderables.forEach(visitor.wrap(nested as  kotlin.collections.Map<String, Any>)::visitRenderable)
        }

        renderables.forEach(visitor::visitRenderable)
    }

    override fun getType(): String {
        return "text"
    }
}
