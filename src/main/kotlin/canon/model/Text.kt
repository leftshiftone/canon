package canon.model

import canon.api.IEvaluator
import kotlin.collections.Map

class Text(id: String?,
           `class`: String?,
           val regex: String?,
           val placeholder: String?,
           val required: Boolean?,
           val name: String?,
           val value: String?) : AbstractRenderable(id, `class`) {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("regex" to regex, "placeholder" to evaluator.evaluate(placeholder?: "", context), "name" to name, "value" to evaluator.evaluate(value ?: "",
        context), "required" to required)
    }
}
