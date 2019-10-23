package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Trigger(@JsonIgnore val id: String?,
                   @JsonIgnore val `class`: String?,
                   val name: String?,
                   val text: String?) : IRenderable {
    override fun toString() = "Trigger(name=$name, text=$text)"
}
