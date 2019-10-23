package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Calendar(@JsonIgnore val id: String?, @JsonIgnore val `class`: String?, val name: String?) : IRenderable {
    override fun toString() = "Calendar(name=$name)"
}
