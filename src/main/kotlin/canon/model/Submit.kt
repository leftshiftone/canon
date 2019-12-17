package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.ILabelAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Submit(@JsonIgnore override val id: String?,
                  @JsonIgnore override val `class`: String?,
                  val text: String?,
                  val name: String?) : IRenderable, IClassAware, ILabelAware {

    override fun getLabel(): String? {
        return text
    }

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("text" to evaluator.evaluate(text, context), "name" to name).plus(toIdAndClassMap(context, evaluator))
    }

    override fun toString() = "Submit(text=$text, name=$name)"
}
