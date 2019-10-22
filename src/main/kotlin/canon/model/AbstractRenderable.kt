package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import canon.support.Base64
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.util.HashMap
import kotlin.collections.Map

abstract class AbstractRenderable(@JsonIgnore val id: String?, @JsonIgnore val `class`: String?) : IRenderable {

    companion object AbstractRenderable {
        private val objectMapper = ObjectMapper().registerModule(KotlinModule())

        fun convertValue(obj: Any): kotlin.collections.Map<String?, Any?> {
            return objectMapper.convertValue(obj, object : TypeReference<MutableMap<String, Any>>() {})
        }
    }

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return convertValue(this);
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is canon.model.AbstractRenderable) return false

        if (id != other.id) return false
        if (`class` != other.`class`) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (`class`?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "AbstractRenderable(id=$id, `class`=$`class`)"
    }

    @Deprecated("Removed soon - value should be called in rain")
    protected fun encode(payload: String?): String? {
        if (payload == null) {
            return null
        }
        val map = HashMap<String, Any>()
        map["payload"] = payload
        return Base64.encode(map)
    }


}
