package canon.api

interface IStackeable : IRenderable {

    val renderables:List<IRenderable>

    override fun accept(visitor: IVisitor) {
        renderables.forEach(visitor::visitRenderable)
    }
}
