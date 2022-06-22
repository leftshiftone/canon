package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.KMap
import canon.support.MapBuilder
import com.fasterxml.jackson.annotation.JsonIgnore

data class Phone(
    @JsonIgnore override val id: String?,
    @JsonIgnore override val `class`: String?,
    @JsonIgnore override val ariaLabel: String?,
    val placeholder: String?,
    val required: Boolean?,
    val name: String?,
    val value: String?
) : IRenderable, IClassAware {

    override fun toMap(context: KMap<String, Any>, evaluator: IEvaluator): KMap<String, Any> {
        val builder = MapBuilder()
        builder.put("placeholder", placeholder)
        builder.put("required", required)
        builder.put("name", name)
        builder.put("value", value) { evaluator.evaluate(it, context) }

        return toIdAndClassAndAriaLabelMap(context, evaluator) + builder.toMap()
    }

    override fun toString() = "Phone(placeholder=$placeholder, required=$required, name=$name, value=$value)"
}
