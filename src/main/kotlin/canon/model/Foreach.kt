package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.IStackable
import canon.api.IVisitor
import canon.support.Iterators
import canon.support.Maps
import com.fasterxml.jackson.annotation.JsonIgnore

data class Foreach(val forEachStmt: String?, val renderable: IRenderable?) : IRenderable, IStackable {

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

    override fun <R> accept(visitor: IVisitor<R>, evaluator: IEvaluator): R {
        val obj: Any? = Maps.getDeep(visitor.getContext(), source)
        if (obj == null)
            return visitor.empty()
        val iterator = Iterators.toIterator(obj)

        return iterator.asSequence().toList()
            .flatMap {
                val nested = visitor.getContext().toMutableMap()
                nested.put(target, it)
                renderables.map { e -> visitor.wrap(nested).visitRenderable(e) }
            }.fold(visitor.empty()) { acc, r -> visitor.merge(acc, r) }
    }

    override fun getType() = "foreach"
    override fun toString() = "Foreach(target='$target', source='$source')"

}
