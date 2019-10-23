package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Submit(@JsonIgnore val id: String?,
                  @JsonIgnore val `class`: String?,
                  val text: String?,
                  val name: String?) : IRenderable {
    override fun toString() = "Submit(text=$text, name=$name)"
}
