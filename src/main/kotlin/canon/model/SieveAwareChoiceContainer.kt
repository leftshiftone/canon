package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class SieveAwareChoiceContainer(@JsonIgnore override val id: String?,
                                     @JsonIgnore override val `class`: String?,
                                     val name: String?,
                                     val sieve: Boolean?,
                                     val required: Boolean?,
                                     @JsonIgnore val renderables: List<IRenderable>?) : AbstractStackable(renderables), IClassAware {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("name" to evaluator.evaluate(name, context),
                "sieve" to sieve,
                "required" to required).plus(toIdAndClassMap(context, evaluator))
    }

    override fun toString() = "SieveAwareChoiceContainer(name=$name, sieve=$sieve, required=$required)"
}
