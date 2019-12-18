package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Camera(@JsonIgnore override val id: String?,
             @JsonIgnore override val `class`: String?,
             val name: String?,
             val required: Boolean?,
             val maxCompressSize: Double?,
             @JsonIgnore val renderables: List<IRenderable>?) : AbstractStackable(renderables), IClassAware {

    override fun toString(): String {
        return "Camera(name=$name, required=$required, maxCompressSize=$maxCompressSize, renderables=$renderables)"
    }

}
