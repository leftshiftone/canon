package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore
import kotlin.collections.Map

data class NewText(@JsonIgnore override val id: String?,
                   @JsonIgnore override val `class`: String?,
                   val regex: String?,
                   val placeholder: String?,
                   val required: Boolean?,
                   val name: String?,
                   val value: String?) : IRenderable, IClassAware {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("regex" to regex,
                "placeholder" to evaluator.evaluate(placeholder ?: "", context),
                "name" to evaluator.evaluate(name ?: "", context),
                "value" to evaluator.evaluate(value ?: "", context),
                "required" to required).plus(toIdAndClassMap(context, evaluator))
    }

    override fun toString() = "NewText(regex=$regex, placeholder=$placeholder, required=$required, name=$name, value=$value)"

}
