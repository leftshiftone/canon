package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Spinner(
    @JsonIgnore override val id: String?,
    @JsonIgnore override val `class`: String?,
    @JsonIgnore override val ariaLabel: String?,
    val min: Double?,
    val max: Double?,
    val step: Double?,
    val value: Double?,
    val name: String?
) : IRenderable, IClassAware {
    override fun toString() = "Spinner(min=$min, max=$max, step=$step, value=$value, name=$name)"
}
