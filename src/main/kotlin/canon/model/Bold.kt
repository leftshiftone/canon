package canon.model

import canon.api.IEvaluator
import kotlin.collections.Map

class Bold(id: String?, `class`: String?, val text: String?) : AbstractRenderable(id, `class`) {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("text" to evaluator.evaluate(text, context))
    }
}
