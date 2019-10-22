package canon.model

import canon.api.IEvaluator
import canon.support.Base64
import kotlin.collections.Map

class Suggestion(id: String?,
                 `class`: String?,
                 val text: String?,
                 val name: String?,
                 val value: String?) : AbstractRenderable(id, `class`) {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("text" to text, "value" to Base64.encodeUTF8String(Base64.decode(value)), "name" to name)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Suggestion) return false
        if (!super.equals(other)) return false

        if (text != other.text) return false
        if (name != other.name) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (text?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (value?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Suggestion(text=$text, name=$name, value=$value) ${super.toString()}"
    }


}
