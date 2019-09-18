package canon.model

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import kotlin.collections.Map

object ModelObjectMapper {

    private val objectMapper : ObjectMapper

    init {
        objectMapper = ObjectMapper().registerModule(KotlinModule())
    }

    fun convertValue(obj: Any) : Map<String, Any> {
        return objectMapper.convertValue(obj, object:TypeReference<MutableMap<String, Any>>() {});
    }


}