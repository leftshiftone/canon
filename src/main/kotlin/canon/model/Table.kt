package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Table(@JsonIgnore val id: String?,
            @JsonIgnore val `class`: String?,
            val name: String?,
            @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(renderables) {

    override fun toString() = "Table(name=$name)"
}
