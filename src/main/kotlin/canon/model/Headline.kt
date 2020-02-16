package canon.model

import canon.api.*
import com.fasterxml.jackson.annotation.JsonIgnore

data class Headline(@JsonIgnore override val id: String?,
                    @JsonIgnore override val `class`:
                    String?, val text: String?) : IRenderable, IClassAware, ILabelAware {

    override fun label(): String? {
        return text
    }

    override fun toMap(context: KMap<String, Any>, evaluator: IEvaluator): KMap<String, Any> {
        val map = HashMap<String, Any>()
        if (text != null && text.isNotBlank())
            map["text"] = evaluator.evaluate(text, context)!!

        return map.plus(toIdAndClassMap(context, evaluator))
    }

    override fun toString() = "Headline(text=$text)"
}
