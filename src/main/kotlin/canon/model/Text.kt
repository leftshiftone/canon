package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.KMap
import canon.support.MapBuilder
import com.fasterxml.jackson.annotation.JsonIgnore

data class Text(
    @JsonIgnore override val id: String?,
    @JsonIgnore override val `class`: String?,
    @JsonIgnore override val ariaLabel: String?,
    val regex: String?,
    val placeholder: String?,
    val required: Boolean?,
    val name: String?,
    val value: String?,
) : IRenderable, IClassAware {

    override fun toMap(context: KMap<String, Any>, evaluator: IEvaluator): KMap<String, Any> {
        val builder = MapBuilder()
        builder.put("regex", regex)
        builder.put("required", required)
        builder.put("name", name) { evaluator.evaluate(it, context) }
        builder.put("value", value) { evaluator.evaluate(it, context) }
        builder.put("placeholder", placeholder) { evaluator.evaluate(it, context) }

        return toIdAndClassAndAriaLabelMap(context, evaluator) + builder.toMap()
    }

    override fun toString() =
        "Text(regex=$regex, placeholder=$placeholder, required=$required, name=$name, value=$value)"
}
