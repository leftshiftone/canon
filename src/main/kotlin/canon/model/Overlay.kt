package canon.model

import canon.api.IClassAware

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Overlay(
    @JsonIgnore override val id: String?,
    @JsonIgnore override val `class`: String?,
    @JsonIgnore override val ariaLabel: String?,
    val trigger: String?,
    @JsonIgnore val renderables: List<IRenderable>?
) : AbstractStackable(renderables), IClassAware {
    override fun toString() = "Overlay(trigger=$trigger)"
}
