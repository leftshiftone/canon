package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Item(@JsonIgnore val id: String?,
           @JsonIgnore val `class`: String?,
           @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(renderables) {

    override fun toString() = "Item { ${renderables?.map { it.toString() }} }"
}
