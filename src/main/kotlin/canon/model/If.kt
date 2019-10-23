package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.IStackeable
import canon.api.IVisitor
import com.fasterxml.jackson.annotation.JsonIgnore


data class If(@JsonIgnore val expression: String?,
              @JsonIgnore val renderable: IRenderable?,
              @JsonIgnore override val renderables: List<IRenderable>?) : IRenderable, IStackeable {

    override fun accept(visitor: IVisitor, evaluator: IEvaluator) {
        if (evaluator.evaluate(expression?.trim(), visitor.getContext())?.toBoolean()!!) visitor.visitRenderable(this.renderable)
    }

    override fun toString() = "If(expression=$expression, renderable=$renderable)"
}
