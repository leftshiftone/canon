package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.ILabelAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Headline(@JsonIgnore override val id: String?,
                    @JsonIgnore override val `class`:
                    String?, val text: String?) : IRenderable, IClassAware, ILabelAware {

    override fun label(): String? {
        return text
    }

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String, Any> {
        val map = HashMap<String, Any>()
        if (text != null && text.isNotBlank())
            map["text"] = evaluator.evaluate(text, context)!!

        return map.plus(toIdAndClassMap(context, evaluator))
    }

    override fun toString() = "Headline(text=$text)"
}
