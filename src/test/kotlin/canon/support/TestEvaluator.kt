package canon.support

import canon.api.IEvaluator

class TestEvaluator : IEvaluator {

    override fun evaluate(name: String?, context: Map<String, Any>): String? {
        return name;
    }


}