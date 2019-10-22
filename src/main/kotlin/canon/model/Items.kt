package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

class Items(id: String?,
            `class`: String?,
            val ordered: Boolean?,
            @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(id, `class`, renderables) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Items) return false
        if (!super.equals(other)) return false

        if (ordered != other.ordered) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (ordered?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Items(ordered=$ordered) ${super.toString()}"
    }

}
