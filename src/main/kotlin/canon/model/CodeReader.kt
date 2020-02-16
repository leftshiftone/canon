package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.KMap
import com.fasterxml.jackson.annotation.JsonIgnore

data class CodeReader(@JsonIgnore override val id: String?,
                      @JsonIgnore override val `class`: String?,
                      val name: String?,
                      val format: String?) : IRenderable, IClassAware {

    override fun toMap(context: KMap<String, Any>, evaluator: IEvaluator): KMap<String, Any> {
        val map = HashMap<String, Any>()
        if (name != null && name.isNotBlank())
            map["name"] = evaluator.evaluate(name, context)
        if (format != null && format.isNotBlank())
            map["format"] = format

        return map + toIdAndClassMap(context, evaluator)
    }

    override fun toString() = "CodeReader(name=$name, format=$format)"

}
