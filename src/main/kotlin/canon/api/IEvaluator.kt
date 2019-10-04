package canon.api

interface IEvaluator {
    fun evaluate(name:String, context:Map<String, Any>):String
}
