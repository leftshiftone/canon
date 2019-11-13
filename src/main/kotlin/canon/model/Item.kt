package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Item(@JsonIgnore override val id: String?,
           @JsonIgnore override val `class`: String?,
           @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(renderables), IClassAware {

    override fun toString() = "Item { ${renderables?.map { it.toString() }} }"
}
