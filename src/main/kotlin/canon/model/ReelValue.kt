package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class ReelValue(
    @JsonIgnore override val id: String?,
    @JsonIgnore override val `class`: String?,
    @JsonIgnore override val ariaLabel: String?,
    val value: String?,
    val valueType: String?
) : IRenderable, IClassAware {
    override fun toString() = "ReelValue(value=$value, valueType=$valueType)"
}
