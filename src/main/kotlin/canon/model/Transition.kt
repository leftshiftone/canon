package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

class Transition(id: String?,
                 `class`: String?,
                 val name: String?,
                 val direction: String?,
                 val wrapped: String?,
                 @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(id, `class`, renderables) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Transition) return false
        if (!super.equals(other)) return false

        if (name != other.name) return false
        if (direction != other.direction) return false
        if (wrapped != other.wrapped) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (direction?.hashCode() ?: 0)
        result = 31 * result + (wrapped?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Transition(name=$name, direction=$direction, wrapped=$wrapped) ${super.toString()}"
    }
}
