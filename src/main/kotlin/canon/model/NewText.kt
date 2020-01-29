package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class NewText(@JsonIgnore override val id: String?,
                   @JsonIgnore override val `class`: String?,
                   val regex: String?,
                   val placeholder: String?,
                   val required: Boolean?,
                   val name: String?,
                   val value: String?,
                   val text: String?) : IRenderable, IClassAware {

    override fun toMap(context: kotlin.collections.Map<String, Any>, evaluator: IEvaluator): kotlin.collections.Map<String?, Any?> {
        return mapOf("regex" to regex,
                "placeholder" to evaluator.evaluate(placeholder ?: "", context),
                "name" to evaluator.evaluate(name ?: "", context),
                "value" to evaluator.evaluate(value ?: "", context),
                "text" to evaluator.evaluate(text, context),
                "required" to required).plus(toIdAndClassMap(context, evaluator))
    }

    override fun toString() = "NewText(regex=$regex, placeholder=$placeholder, required=$required, name=$name, value=$value)"

}
