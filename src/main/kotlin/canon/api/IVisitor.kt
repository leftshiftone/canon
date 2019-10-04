package canon.api

interface IVisitor {

    fun visitRenderable(renderable: IRenderable)

    fun getContext() : Map<String, Any> { return HashMap<String, Any>() }

    fun wrap(context: Map<String, Any>) : IVisitor { return this }

    fun evaluate(expression: String) : String { return expression }

}
