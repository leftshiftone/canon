package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Choice(@JsonIgnore override val id: String?,
                  @JsonIgnore override val `class`: String?,
                  val name: String?,
                  val text: String?,
                  val selected: String?,
                  @JsonIgnore val renderables: List<IRenderable>?) : AbstractStackeable(renderables), IClassAware {

    override fun toMap(context: kotlin.collections.Map<String, Any>, evaluator: IEvaluator): kotlin.collections.Map<String?, Any?> {
        return toIdAndClassMap(context, evaluator) + mapOf(
                "name" to evaluator.evaluate(name, context),
                "text" to evaluator.evaluate(text, context),
                "selected" to evaluator.evaluate(selected, context)?.toBoolean()
        )
    }

    override fun toString() = "Choice(name=$name, text=$text, selected=$selected)"

}
