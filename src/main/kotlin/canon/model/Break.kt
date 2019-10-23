package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Break(@JsonIgnore val id: String?, @JsonIgnore val `class`: String?) : IRenderable {
    override fun toString() = "Break()"
}
