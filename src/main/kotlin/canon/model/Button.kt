package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import canon.support.Base64
import com.fasterxml.jackson.annotation.JsonIgnore
import org.apache.commons.lang3.StringUtils
import kotlin.collections.Map

data class Button(@JsonIgnore override val id: String?,
                  @JsonIgnore override val `class`: String?,
                  val text: String?,
                  val name: String?,
                  var value: String?) : IRenderable, IClassAware {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("text" to evaluator.evaluate(text, context), "value" to Base64.encodeUTF8String(evaluator.evaluate(Base64.decode(value), context)),
                "name" to if (StringUtils.isEmpty(name)) "result" else name).plus(toIdAndClassMap(context, evaluator))
    }

    override fun toString() = "Button(text=$text, name=$name, value=$value)"
}
