package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import canon.support.MapBuilder
import com.fasterxml.jackson.annotation.JsonIgnore

data class NewText(@JsonIgnore override val id: String?,
                   @JsonIgnore override val `class`: String?,
                   val regex: String?,
                   val placeholder: String?,
                   val required: Boolean?,
                   val name: String?,
                   val value: String?,
                   val text: String?) : IRenderable, IClassAware {

    override fun toMap(context: kotlin.collections.Map<String, Any>, evaluator: IEvaluator): kotlin.collections.Map<String, Any> {
        val builder = MapBuilder()
        builder.put("regex", regex)
        builder.put("placeholder", placeholder) {evaluator.evaluate(it, context)}
        builder.put("name", name) {evaluator.evaluate(it, context)}
        builder.put("value", value) {evaluator.evaluate(it, context)}
        builder.put("text", text) {evaluator.evaluate(it, context)}
        builder.put("required", required)

        return builder.toMap().plus(toIdAndClassMap(context, evaluator))
    }

    override fun toString() = "NewText(regex=$regex, placeholder=$placeholder, required=$required, name=$name, value=$value)"

}
