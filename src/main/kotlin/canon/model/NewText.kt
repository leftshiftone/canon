package canon.model

import canon.api.IEvaluator
import kotlin.collections.Map

class NewText(id: String?,
              `class`: String?,
              val regex: String?,
              val placeholder: String?,
              val required: Boolean?,
              val name: String?,
              val value: String?) : AbstractRenderable(id, `class`) {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("regex" to regex, "placeholder" to evaluator.evaluate(placeholder
                ?: "", context), "name" to name, "value" to evaluator.evaluate(value ?: "",
                context), "required" to required)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NewText) return false
        if (!super.equals(other)) return false

        if (regex != other.regex) return false
        if (placeholder != other.placeholder) return false
        if (required != other.required) return false
        if (name != other.name) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (regex?.hashCode() ?: 0)
        result = 31 * result + (placeholder?.hashCode() ?: 0)
        result = 31 * result + (required?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (value?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "NewText(regex=$regex, placeholder=$placeholder, required=$required, name=$name, value=$value) ${super.toString()}"
    }

}
