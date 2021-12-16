package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Reel(
    @JsonIgnore override val id: String?,
    @JsonIgnore override val `class`: String?,
    @JsonIgnore override val ariaLabel: String?,
    val name: String?,
    @JsonIgnore val renderables: List<IRenderable>?
) : AbstractStackable(renderables), IClassAware {
    override fun toString() = "Reel(name=$name)"
}
