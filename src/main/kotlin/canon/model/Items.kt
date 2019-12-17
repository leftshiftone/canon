package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Items(@JsonIgnore override val id: String?,
            @JsonIgnore override val `class`: String?,
            val ordered: Boolean?,
            @JsonIgnore val renderables: List<IRenderable>?) : AbstractStackable(renderables), IClassAware {

    override fun toString() = "Items(ordered=$ordered) { ${renderables?.map { it.toString() }} }"

}
