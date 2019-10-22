package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore
import kotlin.collections.Map

class SieveAwareChoiceContainer(id: String?,
                                `class`: String?,
                                open val name: String?,
                                open val sieve: Boolean?,
                                open val required: Boolean?,
                                @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(id, `class`, renderables) {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("name" to evaluator.evaluate(name, context), "sieve" to sieve, "required" to required)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SieveAwareChoiceContainer) return false
        if (!super.equals(other)) return false

        if (name != other.name) return false
        if (sieve != other.sieve) return false
        if (required != other.required) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (sieve?.hashCode() ?: 0)
        result = 31 * result + (required?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "SieveAwareChoiceContainer(name=$name, sieve=$sieve, required=$required) ${super.toString()}"
    }


}