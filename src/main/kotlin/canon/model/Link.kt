package canon.model

import canon.api.IEvaluator
import kotlin.collections.Map

class Link(id: String?, `class`: String?, val value: String?, val text: String?) : AbstractRenderable(id, `class`) {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("text" to evaluator.evaluate(text, context), "value" to evaluator.evaluate(value, context))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Link) return false
        if (!super.equals(other)) return false

        if (value != other.value) return false
        if (text != other.text) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (value?.hashCode() ?: 0)
        result = 31 * result + (text?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Link(value=$value, text=$text) ${super.toString()}"
    }

}