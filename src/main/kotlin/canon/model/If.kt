package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.IStackeable
import canon.api.IVisitor
import com.fasterxml.jackson.annotation.JsonIgnore


class If (attributeValue: String, renderable: IRenderable,
          @JsonIgnore override val renderables: List<IRenderable>)
    : IRenderable, IStackeable  {

    private val expression: String
    private val renderable : IRenderable

    init {
        this.expression = extractExpression(attributeValue)
        this.renderable = renderable
    }

    override fun accept(visitor: IVisitor, evaluator: IEvaluator) {
        if (evaluate(visitor)) visitor.visitRenderable(this.renderable)
    }

    private fun extractExpression(str: String) : String { return str.trim() }

}
