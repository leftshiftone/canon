package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.KMap
import canon.support.MapBuilder
import com.fasterxml.jackson.annotation.JsonIgnore

data class Choice(
    @JsonIgnore override val id: String?,
    @JsonIgnore override val `class`: String?,
    @JsonIgnore override val ariaLabel: String?,
    val name: String?,
    val text: String?,
    val selected: String?,
    @JsonIgnore val renderables: List<IRenderable>?
) : AbstractStackable(renderables), IClassAware {

    override fun toMap(context: KMap<String, Any>, evaluator: IEvaluator): KMap<String, Any> {
        val builder = MapBuilder()
        if (!name.isNullOrBlank())
            builder.put("name", name) { evaluator.evaluate(name, context) }
        if (!text.isNullOrBlank())
            builder.put("text", text) { evaluator.evaluate(text, context) }
        if (!selected.isNullOrBlank())
            builder.put("selected", selected) { evaluator.evaluate(selected, context).toBoolean() }

        return builder.toMap().plus(toIdAndClassAndAriaLabelMap(context, evaluator))
    }

    override fun toString() =
        "Choice(name=$name, text=$text, selected=$selected) { ${renderables?.map { it.toString() }}"

}
