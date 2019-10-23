package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

@Deprecated("Use Choice instead")
data class Checkbox(@JsonIgnore val id: String?,
                    @JsonIgnore val `class`: String?,
                    val value: String?,
                    val text: String?,
                    val name: String?,
                    val checked: String?) : IRenderable {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("text" to text, "name" to name, "value" to value, "group" to name,
                "checked" to evaluator.evaluate(checked, context))
    }

    override fun toString() = "Checkbox(value=$value, text=$text, name=$name, checked=$checked)"

}
