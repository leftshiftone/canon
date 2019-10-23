package canon.api

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

interface IRenderable : IVisitable {

    companion object {
        private val objectMapper = ObjectMapper().registerModule(KotlinModule())

        fun convertValue(obj: Any): kotlin.collections.Map<String?, Any?> {
            return objectMapper.convertValue(obj, object : TypeReference<MutableMap<String, Any>>() {})
        }
    }

    fun toMap(context: Map<String, Any>, evaluator: IEvaluator) = convertValue(this);

    override fun accept(visitor: IVisitor, evaluator: IEvaluator) {
        visitor.visitRenderable(this)
    }

    @JsonIgnore
    fun getType(): String {
        return this::class.java.canonicalName
    }

}
