package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore
import kotlin.collections.Map

data class SieveAwareChoiceContainer(@JsonIgnore val id: String?,
                                @JsonIgnore val `class`: String?,
                                val name: String?,
                                val sieve: Boolean?,
                                val required: Boolean?,
                                @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(renderables) {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("name" to evaluator.evaluate(name, context), "sieve" to sieve, "required" to required)
    }
    override fun toString() = "SieveAwareChoiceContainer(name=$name, sieve=$sieve, required=$required)"
}
