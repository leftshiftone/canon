package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.KMap
import com.fasterxml.jackson.annotation.JsonIgnore

data class MultipleChoice(@JsonIgnore override val id: String?,
                          @JsonIgnore override val `class`: String?,
                          override val name: String?,
                          override val sieve: Boolean?,
                          override val required: Boolean?,
                          @JsonIgnore override val renderables: List<IRenderable>?) : SieveAwareChoiceContainer(id, `class`, name, sieve, required, renderables), IClassAware {

    override fun toMap(context: KMap<String, Any>, evaluator: IEvaluator): KMap<String, Any> {
        val map = HashMap<String, Any>()
        if (name != null && name.isNotBlank())
            map["name"] = evaluator.evaluate(name, context)!!
        if (sieve != null)
            map["sieve"] = sieve
        if (required != null)
            map["required"] = required

        return toIdAndClassMap(context, evaluator) + map
    }

    override fun toString() = "MultipleChoice(name=$name, sieve=$sieve, required=$required)"
}
