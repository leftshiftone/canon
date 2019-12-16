package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Calendar(@JsonIgnore override val id: String?, @JsonIgnore override val `class`: String?, val name: String?) : IRenderable, IClassAware {
    override fun toString() = "Calendar(name=$name)"
}
