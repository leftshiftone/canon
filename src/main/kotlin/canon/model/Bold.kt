package canon.model

import canon.api.IEvaluator
import kotlin.collections.Map

class Bold(id: String?, `class`: String?, val text: String?) : AbstractRenderable(id, `class`) {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("text" to evaluator.evaluate(text, context))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Bold) return false
        if (!super.equals(other)) return false

        if (text != other.text) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (text?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Bold(text=$text) ${super.toString()}"
    }
    
}
