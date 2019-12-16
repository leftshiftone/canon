package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Transition(@JsonIgnore override val id: String?,
                 @JsonIgnore override val `class`: String?,
                 val name: String?,
                 val direction: String?,
                 val wrapped: String?,
                 @JsonIgnore val renderables: List<IRenderable>?) : AbstractStackeable(renderables), IClassAware {
    override fun toString() = "Transition(name=$name, direction=$direction, wrapped=$wrapped)"
}
