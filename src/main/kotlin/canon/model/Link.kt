package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore
import kotlin.collections.Map

data class Link(@JsonIgnore override val id: String?,
                @JsonIgnore override val `class`: String?,
                val value: String?,
                val text: String?) : IRenderable, IClassAware {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("text" to evaluator.evaluate(text, context),
                "value" to evaluator.evaluate(value, context)).plus(toIdAndClassMap(context, evaluator))
    }

    override fun toString() = "Link(value=$value, text=$text)"
}
