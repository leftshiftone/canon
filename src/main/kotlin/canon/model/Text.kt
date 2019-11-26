package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Text(@JsonIgnore override val id: String?, @JsonIgnore override val `class`: String?, val text: String?) : IRenderable, IClassAware {

    //todo: check if needed
    //override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
    //    return toIdAndClassMap(context, evaluator) + mapOf("text" to evaluator.evaluate(text, context))
    //}

    override fun toString() = "Text(text=$text)"
}
