package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

class MultipleChoice(id: String?,
                     `class`: String?,
                     val name: String?,
                     val sieve: Boolean?,
                     @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(id, `class`, renderables) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MultipleChoice) return false
        if (!super.equals(other)) return false

        if (name != other.name) return false
        if (sieve != other.sieve) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (sieve?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "MultipleChoice(name=$name, sieve=$sieve) ${super.toString()}"
    }

}
