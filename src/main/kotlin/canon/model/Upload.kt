package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Upload(@JsonIgnore val id: String?,
                  @JsonIgnore val `class`: String?,
                  val accept: String?,
                  val name: String?,
                  val text: String?,
                  val maxSize: Double?,
                  val maxCompressionSize: Double?) : IRenderable {
    override fun toString() = "Upload(accept=$accept, name=$name, text=$text, maxSize=$maxSize, maxCompressionSize=$maxCompressionSize)"
}
