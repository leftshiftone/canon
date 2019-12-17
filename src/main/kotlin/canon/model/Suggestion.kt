package canon.model

import canon.api.IClassAware
import canon.api.IClickable
import canon.api.IEvaluator
import canon.api.IRenderable
import canon.support.Base64
import com.fasterxml.jackson.annotation.JsonIgnore

data class Suggestion(@JsonIgnore override val id: String?,
                      @JsonIgnore override val `class`: String?,
                      val text: String?,
                      val name: String?,
                      val value: String?) : IRenderable, IClassAware, IClickable {

    override fun getLabel(): String? {
        return text
    }

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("text" to text,
                "value" to Base64.encodeUTF8String(Base64.decode(value)),
                "name" to name).plus(toIdAndClassMap(context, evaluator))
    }

    override fun toString() = "Suggestion(text=$text, name=$name, value=$value)"
}
