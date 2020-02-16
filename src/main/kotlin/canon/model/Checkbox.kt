package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import canon.support.MapBuilder
import com.fasterxml.jackson.annotation.JsonIgnore

@Deprecated("Use Choice instead")
data class Checkbox(@JsonIgnore override val id: String?,
                    @JsonIgnore override val `class`: String?,
                    val value: String?,
                    val text: String?,
                    val name: String?,
                    val checked: String?) : IRenderable, IClassAware {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String, Any> {
        val builder = MapBuilder()
        builder.put("text", text)
        builder.put("name", name)
        builder.put("value", value)
        builder.put("group", name)
        builder.put("checked", checked) {evaluator.evaluate(it, context)}

        return builder.toMap().plus(toIdAndClassMap(context, evaluator))
    }

    override fun toString() = "Checkbox(value=$value, text=$text, name=$name, checked=$checked)"

}
