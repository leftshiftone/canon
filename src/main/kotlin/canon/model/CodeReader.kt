package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class CodeReader(@JsonIgnore val id: String?,
                      @JsonIgnore val `class`: String?,
                      val name: String?,
                      val format: String?) : IRenderable {

    override fun toString() = "CodeReader(name=$name, format=$format)"

}
