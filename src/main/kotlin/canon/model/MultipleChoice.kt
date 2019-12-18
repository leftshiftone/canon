package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore
import kotlin.collections.Map

data class MultipleChoice(@JsonIgnore override val id: String?,
                          @JsonIgnore override val `class`: String?,
                          val name: String?,
                          val sieve: Boolean?,
                          @JsonIgnore val renderables: List<IRenderable>?) : AbstractStackable(renderables), IClassAware {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return toIdAndClassMap(context, evaluator) + mapOf(
                "name" to evaluator.evaluate(name, context),
                "sieve" to sieve
        )
    }

    override fun toString() = "MultipleChoice(name=$name, sieve=$sieve)"
}
