package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.IStackeable
import canon.api.IVisitor
import com.fasterxml.jackson.annotation.JsonIgnore


class If(@JsonIgnore val expression: String?,
         @JsonIgnore val renderable: IRenderable?,
         @JsonIgnore override val renderables: List<IRenderable>?) : IRenderable, IStackeable {

    override fun accept(visitor: IVisitor, evaluator: IEvaluator) {
        if (evaluator.evaluate(expression?.trim(), visitor.getContext())?.toBoolean()!!) visitor.visitRenderable(this.renderable)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is If) return false

        if (expression != other.expression) return false
        if (renderable != other.renderable) return false

        return true
    }

    override fun hashCode(): Int {
        var result = expression?.hashCode() ?: 0
        result = 31 * result + (renderable?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "If(expression=$expression, renderable=$renderable)"
    }


}
