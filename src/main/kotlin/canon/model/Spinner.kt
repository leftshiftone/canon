package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Spinner(@JsonIgnore val id: String?,
                   @JsonIgnore val `class`: String?,
                   val min: Double?,
                   val max: Double?,
                   val step: Double?,
                   val value: Double?,
                   val name: String?) : IRenderable {
    override fun toString() = "Spinner(min=$min, max=$max, step=$step, value=$value, name=$name)"
}
