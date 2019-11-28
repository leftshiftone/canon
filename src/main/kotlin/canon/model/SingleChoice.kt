package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class SingleChoice(@JsonIgnore override val id: String?,
                        @JsonIgnore override val `class`: String?,
                        val name: String?,
                        val sieve: Boolean?,
                        @JsonIgnore val renderables: List<IRenderable>?) : AbstractStackeable(renderables), IClassAware {

    override fun toMap(context: kotlin.collections.Map<String, Any>, evaluator: IEvaluator): kotlin.collections.Map<String?, Any?> {
        return toIdAndClassMap(context, evaluator) + mapOf(
                "name" to evaluator.evaluate(name, context),
                "sieve" to sieve
        )
    }

    override fun toString() = "SingleChoice(name=$name, sieve=$sieve)"
}
