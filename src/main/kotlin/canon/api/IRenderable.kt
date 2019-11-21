package canon.api

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import kotlin.collections.Map

interface IRenderable : IVisitable {

    companion object {
        private val objectMapper = ObjectMapper().registerModule(KotlinModule())

        fun convertValue(obj: Any): Map<String?, Any?> {
            return objectMapper.convertValue(obj, object : TypeReference<MutableMap<String, Any>>() {})
        }
    }

    @JvmDefault
    fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        val map = convertValue(this)
        return if (this is IClassAware) map.plus(this.toIdAndClassMap(context, evaluator)) else map
    }

    @JvmDefault
    override fun accept(visitor: IVisitor, evaluator: IEvaluator) {
        visitor.visitRenderable(this)
    }

    @JsonIgnore
    @JvmDefault
    fun getType(): String {
        return this::class.java.simpleName.decapitalize()
    }
}
