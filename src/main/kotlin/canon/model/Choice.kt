package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Choice(@JsonIgnore val id: String?,
             @JsonIgnore val `class`: String?,
             val text: String?,
             val selected: Boolean?,
             @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(renderables) {

    override fun toString() = "Choice(text=$text, selected=$selected)"

}
