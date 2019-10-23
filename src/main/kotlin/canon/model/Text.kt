package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Text(@JsonIgnore val id: String?, @JsonIgnore val `class`: String?, val text: String?) : IRenderable {
    override fun toString() = "Text(text=$text)"
}
