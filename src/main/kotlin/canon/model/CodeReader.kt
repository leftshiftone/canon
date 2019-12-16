package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class CodeReader(@JsonIgnore override val id: String?,
                      @JsonIgnore override val `class`: String?,
                      val name: String?,
                      val format: String?) : IRenderable, IClassAware {

    override fun toString() = "CodeReader(name=$name, format=$format)"

}
