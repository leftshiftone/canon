package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.IStackable
import canon.api.IVisitor
import com.fasterxml.jackson.annotation.JsonIgnore


data class If(
    @JsonIgnore val expression: String?,
    @JsonIgnore val renderable: IRenderable?
) : IRenderable, IStackable {

    override fun <R> accept(visitor: IVisitor<R>, evaluator: IEvaluator): R {
        if (expression.isNullOrBlank())
            return visitor.empty()
        val evaluation = evaluator.evaluate(expression.trim(), visitor.getContext()).toBoolean()
        return when (evaluation) {
            true -> visitor.visitRenderable(this.renderable)
            else -> visitor.empty()
        }
    }

    override fun toString() = "If(expression=$expression, renderable=$renderable)"
}
