package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore
import kotlin.collections.Map

data class SingleChoice(@JsonIgnore override val id: String?,
                        @JsonIgnore override val `class`: String?,
                        override val name: String?,
                        override val sieve: Boolean?,
                        override val required: Boolean?,
                        @JsonIgnore override val renderables: List<IRenderable>?) : SieveAwareChoiceContainer(id, `class`, name, sieve, required, renderables), IClassAware {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return toIdAndClassMap(context, evaluator) + mapOf(
                "name" to evaluator.evaluate(name, context),
                "sieve" to sieve,
                "required" to required
        )
    }

    override fun toString() = "SingleChoice(name=$name, sieve=$sieve, required=$required)"
}
