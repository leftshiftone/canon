package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.KMap
import com.fasterxml.jackson.annotation.JsonIgnore

data class Email(@JsonIgnore override val id: String?,
                 @JsonIgnore override val `class`: String?,
                 val placeholder: String?,
                 val required: Boolean?,
                 val name: String?,
                 val value: String?) : IRenderable, IClassAware {

    override fun toMap(context: KMap<String, Any>, evaluator: IEvaluator): KMap<String, Any> {
        val map = HashMap<String, Any>()
        if (placeholder != null && placeholder.isNotBlank())
            map["placeholder"] = placeholder
        if (required != null)
            map["required"] = required
        if (name != null && name.isNotBlank())
            map["name"] = name
        if (value != null && value.isNotBlank())
            map["value"] = evaluator.evaluate(value ?: "", context)!!

        return toIdAndClassMap(context, evaluator) + map
    }

    override fun toString() = "Email(placeholder=$placeholder, required=$required, name=$name, value=$value)"
}
