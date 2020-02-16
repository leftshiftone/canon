package canon.model

import canon.api.*
import canon.support.MapBuilder
import com.fasterxml.jackson.annotation.JsonIgnore

data class Text(@JsonIgnore override val id: String?,
                @JsonIgnore override val `class`: String?,
                val text: String?) : IRenderable, IClassAware, ILabelAware {

    override fun label(): String? {
        return text
    }

    override fun toMap(context: KMap<String, Any>, evaluator: IEvaluator): KMap<String, Any> {
        val builder = MapBuilder()
        builder.put("text", text) {evaluator.evaluate(it, context)}
        return toIdAndClassMap(context, evaluator) + builder.toMap()
    }

    override fun toString() = "Text(text=$text)"
}
