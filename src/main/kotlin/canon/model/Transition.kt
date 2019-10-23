package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Transition(@JsonIgnore val id: String?,
                 @JsonIgnore val `class`: String?,
                 val name: String?,
                 val direction: String?,
                 val wrapped: String?,
                 @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(renderables) {
    override fun toString() = "Transition(name=$name, direction=$direction, wrapped=$wrapped)"
}
