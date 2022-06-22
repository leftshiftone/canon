package canon.model

import canon.api.*
import canon.support.Base64
import canon.support.MapBuilder
import com.fasterxml.jackson.annotation.JsonIgnore

data class Suggestion(
    @JsonIgnore override val id: String?,
    @JsonIgnore override val `class`: String?,
    @JsonIgnore override val ariaLabel: String?,
    val text: String?,
    val name: String?,
    val value: String?
) : IRenderable, IClassAware, IClickable, IValueAware {

    override fun label(): String? {
        return text
    }

    override fun name(): String? {
        return name
    }

    override fun value(): String? {
        return value
    }

    override fun toMap(context: KMap<String, Any>, evaluator: IEvaluator): KMap<String, Any> {
        val builder = MapBuilder()
        builder.put("text", text)
        builder.put("name", name)
        builder.put("value", value) { Base64.encodeUTF8String(Base64.decode(it as String?))!! }

        return builder.toMap().plus(toIdAndClassAndAriaLabelMap(context, evaluator))
    }

    override fun toString() = "Suggestion(text=$text, name=$name, value=$value)"
}
