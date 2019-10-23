package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class ReelValue(@JsonIgnore val id: String,
                     @JsonIgnore val `class`: String?,
                     val value: String?,
                     val valueType: String?) : IRenderable {
    override fun toString() = "ReelValue(value=$value, valueType=$valueType)"
}
