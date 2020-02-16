package canon.support

import canon.api.IEvaluator

class TestEvaluator : IEvaluator {

    override fun evaluate(expression: String, context: Map<String, Any>): String {
        return expression
                .removePrefix("{{")
                .removeSuffix("}}")
    }
}
