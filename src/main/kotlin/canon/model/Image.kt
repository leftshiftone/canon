package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Image(@JsonIgnore val id: String?, @JsonIgnore val `class`: String?,
                 val src: String?,
                 val width: String?,
                 val height: String?,
                 val alt: String?) : IRenderable {

    override fun toString() = "Image(src=$src, width=$width, height=$height, alt=$alt)"
}
