package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.KMap
import com.fasterxml.jackson.annotation.JsonIgnore

data class Link(
    @JsonIgnore override val id: String?,
    @JsonIgnore override val `class`: String?,
    @JsonIgnore override val ariaLabel: String?,
    val value: String?,
    val text: String?
) : IRenderable, IClassAware {

    override fun toMap(context: KMap<String, Any>, evaluator: IEvaluator): KMap<String, Any> {
        val map = HashMap<String, Any>()
        if (text != null && text.isNotBlank())
            map["text"] = evaluator.evaluate(text, context)
        if (value != null && value.isNotBlank())
            map["value"] = evaluator.evaluate(value, context)

        return map.plus(toIdAndClassAndAriaLabelMap(context, evaluator))
    }

    override fun toString() = "Link(value=$value, text=$text)"
}
