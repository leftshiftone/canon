package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Upload(@JsonIgnore override val id: String?,
                  @JsonIgnore override val `class`: String?,
                  val accept: String?,
                  val name: String?,
                  val text: String?,
                  val maxSize: Double?,
                  val maxCompressionSize: Double?) : IRenderable, IClassAware {
    override fun toString() = "Upload(accept=$accept, name=$name, text=$text, maxSize=$maxSize, maxCompressionSize=$maxCompressionSize)"
}
