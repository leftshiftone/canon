package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class SingleChoice(@JsonIgnore val id: String?,
                   @JsonIgnore val `class`: String?,
                   val name: String?,
                   val sieve: Boolean?,
                   @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(renderables) {
    override fun toString() = "SingleChoice(name=$name, sieve=$sieve)"
}
