package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import org.apache.commons.lang3.StringUtils
import kotlin.collections.Map

class Slider(id: String?,
             `class`: String?,
             val min: Double?,
             val max: Double?,
             val step: Double?,
             val value: Double?,
             val name: String?,
             val values: String?) : AbstractRenderable(id, `class`), IRenderable {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {

        var valuesArray = arrayOf<String>()

        if (StringUtils.isNotEmpty(values)) {
            val values = evaluator.evaluate(values, context)
            valuesArray = StringUtils.split(values, ",")
        }

        return mapOf("name" to evaluator.evaluate(name, context), "min" to min, "max" to max, "step" to step,
                "value" to value, "values" to valuesArray)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Slider) return false
        if (!super.equals(other)) return false

        if (min != other.min) return false
        if (max != other.max) return false
        if (step != other.step) return false
        if (value != other.value) return false
        if (name != other.name) return false
        if (values != other.values) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (min?.hashCode() ?: 0)
        result = 31 * result + (max?.hashCode() ?: 0)
        result = 31 * result + (step?.hashCode() ?: 0)
        result = 31 * result + (value?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (values?.hashCode() ?: 0)
        return result
    }


}
