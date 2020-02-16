package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.ILabelAware
import canon.api.IRenderable
import canon.support.MapBuilder
import com.fasterxml.jackson.annotation.JsonIgnore

data class Submit(@JsonIgnore override val id: String?,
                  @JsonIgnore override val `class`: String?,
                  val text: String?,
                  val name: String?) : IRenderable, IClassAware, ILabelAware {

    override fun label(): String? {
        return text
    }

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String, Any> {
        val builder = MapBuilder()
        builder.put("text", text) {evaluator.evaluate(it, context)}
        builder.put("name", name)

        return builder.toMap().plus(toIdAndClassMap(context, evaluator))
    }

    override fun toString() = "Submit(text=$text, name=$name)"
}
