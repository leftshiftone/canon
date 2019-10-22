package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import kotlin.collections.Map

@Deprecated("Use Choice instead")
class Checkbox(id: String?,
               `class`: String?,
               val value: String?,
               val text: String?,
               val name: String?,
               val checked: String?) : AbstractRenderable(id, `class`) {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("text" to text, "name" to name, "value" to value, "group" to name,
                "checked" to evaluator.evaluate(checked, context))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Checkbox) return false
        if (!super.equals(other)) return false

        if (value != other.value) return false
        if (text != other.text) return false
        if (name != other.name) return false
        if (checked != other.checked) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (value?.hashCode() ?: 0)
        result = 31 * result + (text?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (checked?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Checkbox(value=$value, text=$text, name=$name, checked=$checked) ${super.toString()}"
    }

}