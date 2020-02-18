package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.IStackable
import canon.api.IVisitor
import com.fasterxml.jackson.annotation.JsonIgnore

abstract class AbstractStackable(@JsonIgnore private val renderables: List<IRenderable>?) : IRenderable, IStackable {

    override fun <R> accept(visitor: IVisitor<R>, evaluator: IEvaluator): R {
        if (renderables.isNullOrEmpty())
            return visitor.empty()
        return renderables.map { e -> visitor.visitRenderable(e) }.reduce { a, b -> visitor.merge(a, b) }
    }
}
