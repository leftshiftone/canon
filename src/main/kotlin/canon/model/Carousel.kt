package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Carousel(@JsonIgnore override val id: String?,
               @JsonIgnore override val `class`: String?,
               val text: String?,
               val name: String?,
               val selected: Boolean?,
               @JsonIgnore val renderables: List<IRenderable>?) : AbstractStackeable(renderables), IClassAware {
    override fun toString() = "Carousel(text=$text, name=$name, selected=$selected)"
}
