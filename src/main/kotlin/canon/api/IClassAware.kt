package canon.api

import canon.support.MapBuilder

interface IClassAware {
    val id: String?
    val `class`: String?

    fun toIdAndClassMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String, Any> {
        val builder = MapBuilder()
        builder.put("id", id) {evaluator.evaluate(it, context)}
        builder.put("class", `class`) {evaluator.evaluate(it, context)}

        return builder.toMap()
    }
}
