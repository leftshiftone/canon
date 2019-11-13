package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Overlays(@JsonIgnore override val id: String?, @JsonIgnore override val `class`: String?,
               val trigger: String?,
               @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(renderables), IClassAware {
    override fun toString() = "Overlays(trigger=$trigger)"
}
