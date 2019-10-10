package canon.api

interface IStackeable : IRenderable {

    val renderables:List<IRenderable>?

    override fun accept(visitor: IVisitor, evaluator: IEvaluator) {
        renderables?.forEach(visitor::visitRenderable)
    }
}
