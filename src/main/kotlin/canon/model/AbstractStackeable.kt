package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.IStackeable
import canon.api.IVisitor
import com.fasterxml.jackson.annotation.JsonIgnore

abstract class AbstractStackeable(@JsonIgnore private val renderables: List<IRenderable>?) : IRenderable, IStackeable {

    override fun accept(visitor: IVisitor, evaluator: IEvaluator) {
        renderables?.forEach(visitor::visitRenderable)
    }
}
