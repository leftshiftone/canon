package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

class Overlays(id: String?, `class`: String?,
               val trigger: String?,
               @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(id, `class`, renderables) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Overlays) return false
        if (!super.equals(other)) return false

        if (trigger != other.trigger) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (trigger?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Overlays(trigger=$trigger) ${super.toString()}"
    }


}
