package canon.api

interface IRenderable : IVisitable {

    override fun accept(visitor: IVisitor) { visitor.visitRenderable(this) }

    fun toMap(context: Map<String,Any>) : Map<String, Any> { return HashMap<String,Any>() }
}