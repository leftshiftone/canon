package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

class Selection(id: String?,
                `class`: String?,
                val name: String?,
                val countdownInSec: Int?,
                @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(id, `class`, renderables) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Selection) return false
        if (!super.equals(other)) return false

        if (name != other.name) return false
        if (countdownInSec != other.countdownInSec) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (countdownInSec ?: 0)
        return result
    }

    override fun toString(): String {
        return "Selection(name=$name, countdownInSec=$countdownInSec) ${super.toString()}"
    }
}
