package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class SelectionContent(@JsonIgnore override val id: String?,
                            @JsonIgnore override val `class`: String?,
                            val name: String?,
                            @JsonIgnore val renderables: List<IRenderable>?) : AbstractStackable(renderables), IClassAware {
    override fun toString() = "Selection(name=$name, ${renderables?.map { it.toString() }})"
}
