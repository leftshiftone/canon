package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore
import kotlin.collections.Map

data class Link(@JsonIgnore val id: String?, @JsonIgnore val `class`: String?, val value: String?, val text: String?) : IRenderable {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("text" to evaluator.evaluate(text, context), "value" to evaluator.evaluate(value, context))
    }

    override fun toString() = "Link(value=$value, text=$text)"
}
