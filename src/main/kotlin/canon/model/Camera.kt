package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

class Camera(id: String?,
             `class`: String?,
             val name: String?,
             val required: Boolean?,
             val maxCompressSize: Double?,
             @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(id, `class`, renderables) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Camera) return false
        if (!super.equals(other)) return false

        if (name != other.name) return false
        if (required != other.required) return false
        if (maxCompressSize != other.maxCompressSize) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (required?.hashCode() ?: 0)
        result = 31 * result + (maxCompressSize?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Camera(name=$name, required=$required, maxCompressSize=$maxCompressSize, renderables=$renderables) ${super.toString()}"
    }

}
