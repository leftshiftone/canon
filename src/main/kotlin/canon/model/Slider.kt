package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore
import org.apache.commons.lang3.StringUtils
import kotlin.collections.Map

data class Slider(@JsonIgnore override val id: String?,
                  @JsonIgnore override val `class`: String?,
                  val min: Double?,
                  val max: Double?,
                  val step: Double?,
                  val value: Double?,
                  val name: String?,
                  val values: String?) : IRenderable, IClassAware {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {

        var valuesArray = arrayOf<String>()

        if (StringUtils.isNotEmpty(values)) {
            val values = evaluator.evaluate(values, context)
            valuesArray = StringUtils.split(values, ",")
        }

        return mapOf("name" to evaluator.evaluate(name, context),
                "min" to min,
                "max" to max,
                "step" to step,
                "value" to value,
                "values" to valuesArray).plus(toIdAndClassMap(context, evaluator))
    }
}
