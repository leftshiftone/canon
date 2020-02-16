package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import canon.support.MapBuilder
import com.fasterxml.jackson.annotation.JsonIgnore

data class Slider(@JsonIgnore override val id: String?,
                  @JsonIgnore override val `class`: String?,
                  val min: Double?,
                  val max: Double?,
                  val step: Double?,
                  val value: Double?,
                  val name: String?,
                  val values: String?) : IRenderable, IClassAware {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String, Any> {
        val builder = MapBuilder()
        builder.put("values", values, ArrayList<Any>()) {evaluator.evaluate(it, context).split(",")}
        builder.put("name", name) {evaluator.evaluate(it, context)}
        builder.put("min", min)
        builder.put("max", max)
        builder.put("step", step)
        builder.put("value", value)

        return toIdAndClassMap(context, evaluator) + builder.toMap()
    }
}
