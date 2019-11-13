package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Selection(@JsonIgnore override val id: String?,
                @JsonIgnore override val `class`: String?,
                val name: String?,
                val countdownInSec: Int?,
                @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(renderables), IClassAware {
    override fun toString() = "Selection(name=$name, countdownInSec=$countdownInSec)"
}
