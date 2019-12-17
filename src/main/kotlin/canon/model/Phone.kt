package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Phone(@JsonIgnore override val id: String?,
                 @JsonIgnore override val `class`: String?,
                 val placeholder: String?,
                 val required: Boolean?,
                 val name: String?,
                 val value: String?) : IRenderable, IClassAware {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return toIdAndClassMap(context, evaluator) + mapOf(
                "placeholder" to placeholder,
                "required" to required,
                "name" to name,
                "value" to evaluator.evaluate(value ?: "", context)
        )
    }

    override fun toString() = "Phone(placeholder=$placeholder, required=$required, name=$name, value=$value)"
}
