package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore
import kotlin.collections.Map

data class CodeReader(@JsonIgnore override val id: String?,
                      @JsonIgnore override val `class`: String?,
                      val name: String?,
                      val format: String?) : IRenderable, IClassAware {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf(
                "name" to evaluator.evaluate(name ?: "", context),
                "format" to format
        ) + toIdAndClassMap(context, evaluator)
    }

    override fun toString() = "CodeReader(name=$name, format=$format)"

}
