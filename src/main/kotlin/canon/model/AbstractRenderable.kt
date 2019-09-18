package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

abstract class AbstractRenderable(@JsonIgnore val id:String, @JsonIgnore val `class`:String) : IRenderable {

    companion object AbstractRenderable {
        private val objectMapper = ObjectMapper().registerModule(KotlinModule())

        fun convertValue(obj: Any) : Map<String, Any> {
            return objectMapper.convertValue(obj, object: TypeReference<MutableMap<String, Any>>() {})
        }
    }

    override fun toMap(context: Map<String, Any>): Map<String, Any> {
        return convertValue(this);
    }
}
