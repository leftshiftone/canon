package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Selection(@JsonIgnore val id: String?,
                @JsonIgnore val `class`: String?,
                val name: String?,
                val countdownInSec: Int?,
                @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(renderables) {
    override fun toString() = "Selection(name=$name, countdownInSec=$countdownInSec)"
}
