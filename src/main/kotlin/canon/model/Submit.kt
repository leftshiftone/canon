package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Submit(@JsonIgnore override val id: String?,
                  @JsonIgnore override val `class`: String?,
                  val text: String?,
                  val name: String?) : IRenderable, IClassAware {
    override fun toString() = "Submit(text=$text, name=$name)"
}
