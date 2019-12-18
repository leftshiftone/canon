package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Col(@JsonIgnore override val id: String?,
          @JsonIgnore override val `class`: String?,
          @JsonIgnore val renderables: List<IRenderable>?) : AbstractStackable(renderables), IClassAware {

    override fun toString() = "Col() { ${renderables?.map { it.toString() }} }"

}
