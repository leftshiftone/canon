package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Camera(@JsonIgnore val id: String?,
             @JsonIgnore val `class`: String?,
             val name: String?,
             val required: Boolean?,
             val maxCompressSize: Double?,
             @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(renderables) {

    override fun toString(): String {
        return "Camera(name=$name, required=$required, maxCompressSize=$maxCompressSize, renderables=$renderables)"
    }

}
