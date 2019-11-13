package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore
import kotlin.collections.Map

@Deprecated("Use Choice instead")
data class Checkbox(@JsonIgnore override val id: String?,
                    @JsonIgnore override val `class`: String?,
                    val value: String?,
                    val text: String?,
                    val name: String?,
                    val checked: String?) : IRenderable, IClassAware {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("text" to text, "name" to name, "value" to value, "group" to name,
                "checked" to evaluator.evaluate(checked, context)).plus(toIdAndClassMap(context, evaluator))
    }

    override fun toString() = "Checkbox(value=$value, text=$text, name=$name, checked=$checked)"

}
