package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.KMap
import canon.support.MapBuilder
import com.fasterxml.jackson.annotation.JsonIgnore

data class SingleChoice(@JsonIgnore override val id: String?,
                        @JsonIgnore override val `class`: String?,
                        override val name: String?,
                        override val sieve: Boolean?,
                        override val required: Boolean?,
                        @JsonIgnore override val renderables: List<IRenderable>?) : SieveAwareChoiceContainer(id, `class`, name, sieve, required, renderables), IClassAware {

    override fun toMap(context: KMap<String, Any>, evaluator: IEvaluator): KMap<String, Any> {
        val builder = MapBuilder()
        builder.put("name", name) {evaluator.evaluate(it, context)}
        builder.put("sieve", sieve)
        builder.put("required", required)

        return toIdAndClassMap(context, evaluator) + builder.toMap()
    }

    override fun toString() = "SingleChoice(name=$name, sieve=$sieve, required=$required)"
}
