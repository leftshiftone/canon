package canon.api

interface IVisitor<R> {

    fun visitRenderable(renderable: IRenderable?):R

    fun getContext() : Map<String, Any>

    fun wrap(context: Map<String, Any>) : IVisitor<R> { return this }

    fun merge(r1:R, r2:R):R
    fun empty():R
}
