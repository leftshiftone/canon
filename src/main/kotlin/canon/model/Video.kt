package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Video(@JsonIgnore val id: String?,
                 @JsonIgnore val `class`: String?,
                 val src: String?) : IRenderable {
    override fun toString() = "Video(src=$src)"
}
