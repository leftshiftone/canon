package canon.api

interface IRenderable : IVisitable {

    override fun accept(visitor: IVisitor, evaluator: IEvaluator) { visitor.visitRenderable(this) }

    fun toMap(context: Map<String, Any>, evaluator: IEvaluator) : Map<String, Any> { return HashMap() }
}
