package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.ILabelAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Label(@JsonIgnore override val id: String?,
                 @JsonIgnore override val `class`: String?,
                 val text: String?) : IRenderable, IClassAware, ILabelAware {

    override fun getLabel(): String? {
        return text
    }

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return toIdAndClassMap(context, evaluator) + mapOf("text" to evaluator.evaluate(text, context))
    }

    override fun toString() = "Label(text=$text)"
}
