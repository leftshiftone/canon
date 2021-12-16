package canon.model

import canon.api.IClassAware

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Break(
    @JsonIgnore override val id: String?,
    @JsonIgnore override val `class`: String?,
    @JsonIgnore override val ariaLabel: String?
) : IRenderable, IClassAware {
    override fun toString() = "Break()"
}
