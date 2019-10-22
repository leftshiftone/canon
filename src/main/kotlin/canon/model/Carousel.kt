package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

class Carousel(id: String?,
               `class`: String?,
               val text: String?,
               val name: String?,
               val selected: Boolean?,
               @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(id, `class`, renderables) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Carousel) return false
        if (!super.equals(other)) return false

        if (text != other.text) return false
        if (name != other.name) return false
        if (selected != other.selected) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (text?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (selected?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Carousel(text=$text, name=$name, selected=$selected) ${super.toString()}"
    }
    
}
