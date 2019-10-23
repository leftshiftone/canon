package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.IStackeable
import canon.api.IVisitor
import com.fasterxml.jackson.annotation.JsonIgnore

abstract class AbstractStackeable(@JsonIgnore override val renderables: List<IRenderable>?) : IRenderable, IStackeable {

    override fun accept(visitor: IVisitor, evaluator: IEvaluator) {
        renderables?.forEach(visitor::visitRenderable)
    }


}
