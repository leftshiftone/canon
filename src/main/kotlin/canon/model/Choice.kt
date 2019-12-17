package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Choice(@JsonIgnore override val id: String?,
                  @JsonIgnore override val `class`: String?,
                  val text: String?,
                  val selected: String?,
                  @JsonIgnore val renderables: List<IRenderable>?) : AbstractStackable(renderables), IClassAware {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return toIdAndClassMap(context, evaluator) + mapOf(
                "text" to evaluator.evaluate(text, context),
                "selected" to evaluator.evaluate(selected, context)?.toBoolean()
        )
    }

    override fun toString() = "Choice(text=$text, selected=$selected)"

}
