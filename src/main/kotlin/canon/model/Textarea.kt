package canon.model

import canon.api.IEvaluator
import kotlin.collections.Map

class Textarea(id: String?,
               `class`: String?,
               val placeholder: String?,
               val name: String?,
               val value: String?,
               val required: Boolean?,
               val rows: Int?,
               val cols: Int?) : AbstractRenderable(id, `class`) {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("cols" to cols, "rows" to rows, "placeholder" to evaluator.evaluate(placeholder ?: "", context),
                "name" to name, "value" to evaluator.evaluate(value ?: "", context), "required" to required)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Textarea) return false
        if (!super.equals(other)) return false

        if (placeholder != other.placeholder) return false
        if (name != other.name) return false
        if (value != other.value) return false
        if (required != other.required) return false
        if (rows != other.rows) return false
        if (cols != other.cols) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (placeholder?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (value?.hashCode() ?: 0)
        result = 31 * result + (required?.hashCode() ?: 0)
        result = 31 * result + (rows ?: 0)
        result = 31 * result + (cols ?: 0)
        return result
    }

    override fun toString(): String {
        return "Textarea(placeholder=$placeholder, name=$name, value=$value, required=$required, rows=$rows, cols=$cols) ${super.toString()}"
    }


}
