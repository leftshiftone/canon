package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.IStackable
import canon.api.IVisitor
import com.fasterxml.jackson.annotation.JsonIgnore

abstract class AbstractStackable(@JsonIgnore private val renderables: List<IRenderable>?) : IRenderable, IStackable {

    override fun accept(visitor: IVisitor, evaluator: IEvaluator) {
        renderables?.forEach(visitor::visitRenderable)
    }
}
