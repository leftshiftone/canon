package canon.model

import canon.api.IEvaluator
import kotlin.collections.Map

class Textarea(id: String?,
               `class`: String?,
               val placeholder: String?,
               val name: String?,
               val value: String?,
               val required:Boolean?,
               val rows: Int?,
               val cols: Int?) : AbstractRenderable(id, `class`) {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("cols" to cols, "rows" to rows, "placeholder" to evaluator.evaluate(placeholder ?: "", context),
            "name" to name, "value" to evaluator.evaluate(value ?:"", context), "required" to required)
    }
}
