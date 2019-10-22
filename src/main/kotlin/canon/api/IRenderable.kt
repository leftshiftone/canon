package canon.api

import com.fasterxml.jackson.annotation.JsonIgnore

interface IRenderable : IVisitable {

    override fun accept(visitor: IVisitor, evaluator: IEvaluator) {
        visitor.visitRenderable(this)
    }

    fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return HashMap()
    }

    @JsonIgnore
    fun getType(): String {
        return this::class.java.canonicalName
    }
    
}
