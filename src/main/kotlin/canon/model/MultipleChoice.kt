package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class MultipleChoice(@JsonIgnore val id: String?,
                     @JsonIgnore val `class`: String?,
                     val name: String?,
                     val sieve: Boolean?,
                     @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(renderables) {

    override fun toString() = "MultipleChoice(name=$name, sieve=$sieve)"
}
