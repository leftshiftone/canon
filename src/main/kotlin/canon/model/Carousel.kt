package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Carousel(@JsonIgnore val id: String?,
               @JsonIgnore val `class`: String?,
               val text: String?,
               val name: String?,
               val selected: Boolean?,
               @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(renderables) {
    override fun toString() = "Carousel(text=$text, name=$name, selected=$selected)"
}
