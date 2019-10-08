package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import kotlin.collections.Map

@Deprecated("Use Choice instead")
class Checkbox(id: String,
               `class`: String,
               val value: String,
               val text: String,
               val name: String,
               val checked: String) : AbstractRenderable(id, `class`) {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String, Any> {
        return mapOf("text" to text, "name" to name, "value" to value, "group" to name,
                "checked" to evaluator.evaluate(checked, context))
    }
}