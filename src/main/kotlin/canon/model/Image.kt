package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Image(@JsonIgnore override val id: String?, @JsonIgnore override val `class`: String?,
                 val src: String?,
                 val width: String?,
                 val height: String?,
                 val alt: String?) : IRenderable, IClassAware {

    override fun toString() = "Image(src=$src, width=$width, height=$height, alt=$alt)"
}
