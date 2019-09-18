package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore
import kotlin.collections.Map

abstract class AbstractRenderable(@JsonIgnore val id:String, @JsonIgnore val `class`:String) : IRenderable {

    override fun toMap(context: Map<String, Any>): Map<String, Any> {
        return ModelObjectMapper.convertValue(this);
    }
}
