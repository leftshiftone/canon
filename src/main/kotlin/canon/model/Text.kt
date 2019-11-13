package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Text(@JsonIgnore override val id: String?, @JsonIgnore override val `class`: String?, val text: String?) : IRenderable, IClassAware {
    override fun toString() = "Text(text=$text)"
}
