package canon.api

import kotlin.collections.Map;

interface IClassAware {
    val id: String?
    val `class`: String?

    fun toIdAndClassMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        val map = HashMap<String?,Any?>()
        if (id != null) map["id"] = evaluator.evaluate(id,context)
        if (`class` != null) map["class"] = evaluator.evaluate(`class`, context)
        return map
    }
}