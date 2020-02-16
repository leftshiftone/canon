package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import canon.support.MapBuilder
import com.fasterxml.jackson.annotation.JsonIgnore

data class Bold(@JsonIgnore override val id: String?, @JsonIgnore override val `class`: String?, val text: String?) : IRenderable, IClassAware {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String, Any> {
        val builder = MapBuilder()
        builder.put("text", text) {evaluator.evaluate(it, context)}

        return builder.toMap().plus(toIdAndClassMap(context, evaluator))
    }

    override fun toString() = "Bold(text=$text)"
}
