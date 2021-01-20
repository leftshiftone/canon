package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.KMap
import com.fasterxml.jackson.annotation.JsonIgnore

data class Rating(val enabled: String?,
                  @JsonIgnore val renderables: List<IRenderable>?) : AbstractStackable(renderables) {

    override fun toString() = "Rating(enabled=$enabled) { ${renderables?.map { it.toString() }}"

    override fun toMap(context: KMap<String, Any>, evaluator: IEvaluator): KMap<String, Any> {
        val map = HashMap<String, Any>()
        if (enabled != null && enabled.isNotBlank()) {
            val evaluatedEnabled = evaluator.evaluate(enabled, context);
            if (evaluatedEnabled == "true" || evaluatedEnabled == "false")
                map["enabled"] = evaluatedEnabled.toBoolean()
        }
        return map
    }
}