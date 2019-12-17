package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.IStackable
import canon.api.IVisitor
import com.fasterxml.jackson.annotation.JsonIgnore


data class If(@JsonIgnore val expression: String?,
              @JsonIgnore val renderable: IRenderable?,
              @JsonIgnore private val renderables: List<IRenderable>?) : IRenderable, IStackable {

    override fun accept(visitor: IVisitor, evaluator: IEvaluator) {
        if (evaluator.evaluate(expression?.trim(), visitor.getContext())?.toBoolean()!!) visitor.visitRenderable(this.renderable)
    }

    override fun toString() = "If(expression=$expression, renderable=$renderable)"
}
