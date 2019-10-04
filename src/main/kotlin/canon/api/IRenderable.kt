package canon.api

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

interface IRenderable : IVisitable {

    override fun accept(visitor: IVisitor) { visitor.visitRenderable(this) }

    fun toMap(context: Map<String,Any>) : Map<String, Any> { return HashMap() }

    @JsonIgnore
    fun getType() : String { return this::class.java.canonicalName }
}
