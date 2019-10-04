package canon.model

import canon.api.IRenderable
import canon.api.IStackeable
import canon.api.IVisitor
import canon.support.Iterators
import canon.support.Maps
import com.fasterxml.jackson.annotation.JsonIgnore
import kotlin.collections.Map

class Foreach (forEachStmt: String, renderables: IRenderable) : IRenderable, IStackeable {

    @JsonIgnore
    private val target: String
    @JsonIgnore
    private val source: String
    override val renderables: List<IRenderable>

    init {
        this.target = getTarget(forEachStmt)
        this.source = getSource(forEachStmt)
        this.renderables = listOf(renderables)
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

    override fun accept(visitor: IVisitor) {
        val iterator = Iterators.toIterator(Maps.getDeep(visitor.getContext(), source))

        while (iterator.hasNext()) {
            val nested = HashMap(visitor.getContext()).put(target, iterator.next())
            renderables.forEach(visitor.wrap(nested as Map<String, Any>)::visitRenderable)
        }

        renderables.forEach(visitor::visitRenderable)
    }

    override fun getType(): String {
        return "text"
    }
}