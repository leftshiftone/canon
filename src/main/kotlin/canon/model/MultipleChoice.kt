package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class MultipleChoice(@JsonIgnore override val id: String?,
                     @JsonIgnore override val `class`: String?,
                     val name: String?,
                     val sieve: Boolean?,
                     @JsonIgnore val renderables: List<IRenderable>?) : AbstractStackeable(renderables), IClassAware {

    override fun toString() = "MultipleChoice(name=$name, sieve=$sieve)"
}
