package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import canon.support.MapBuilder
import com.fasterxml.jackson.annotation.JsonIgnore

data class Textarea(@JsonIgnore override val id: String?,
                    @JsonIgnore override val `class`: String?,
                    val placeholder: String?,
                    val name: String?,
                    val value: String?,
                    val required: Boolean?,
                    val rows: Int?,
                    val cols: Int?) : IRenderable, IClassAware {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String, Any> {
        val builder = MapBuilder()
        builder.put("cols", cols)
        builder.put("rows", rows)
        builder.put("name", name)
        builder.put("value", value) {evaluator.evaluate(it, context)}
        builder.put("placeholder", placeholder) {evaluator.evaluate(it, context)}
        builder.put("required", required)

        return builder.toMap().plus(toIdAndClassMap(context, evaluator))
    }

    override fun toString() = "Textarea(placeholder=$placeholder, name=$name, value=$value, required=$required, rows=$rows, cols=$cols)"
}
