package canon.model

import canon.api.*
import com.fasterxml.jackson.annotation.JsonIgnore

data class Headline(
    @JsonIgnore override val id: String?,
    @JsonIgnore override val `class`: String?,
    @JsonIgnore override val ariaLabel: String?,
    val text: String?,
    val level: String?
) : IRenderable, IClassAware, ILabelAware {

    override fun label(): String? {
        return text
    }

    override fun toMap(context: KMap<String, Any>, evaluator: IEvaluator): KMap<String, Any> {
        val map = HashMap<String, Any>()
        if (text != null && text.isNotBlank())
            map["text"] = evaluator.evaluate(text, context)
        if (level != null && level.isNotBlank())
            map["level"] = evaluator.evaluate(level, context)

        return map.plus(toIdAndClassAndAriaLabelMap(context, evaluator))
    }

    override fun toString() = "Headline(text=$text, level=$level)"
}
