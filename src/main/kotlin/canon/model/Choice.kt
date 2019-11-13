package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Choice(@JsonIgnore override val id: String?,
             @JsonIgnore override val `class`: String?,
             val text: String?,
             val selected: Boolean?,
             @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(renderables), IClassAware {

    override fun toString() = "Choice(text=$text, selected=$selected)"

}
