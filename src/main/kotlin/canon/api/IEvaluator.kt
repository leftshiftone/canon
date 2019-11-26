package canon.api

interface IEvaluator {

    fun evaluate(expression: String?, context: Map<String, Any>): String?
}
