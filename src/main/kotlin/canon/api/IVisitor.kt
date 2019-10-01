package canon.api

interface IVisitor {

    fun visitRenderable(renderable: IRenderable)

    fun getContext() : Map<String, Any>
}
