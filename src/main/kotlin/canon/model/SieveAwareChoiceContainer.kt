package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

abstract class SieveAwareChoiceContainer(@JsonIgnore override val id: String?,
                                         @JsonIgnore override val `class`: String?,
                                         open val name: String?,
                                         open val sieve: Boolean?,
                                         open val required: Boolean?,
                                         @JsonIgnore open val renderables: List<IRenderable>?) : AbstractStackable(renderables), IClassAware {

    override fun toString() = "SieveAwareChoiceContainer(name=$name, sieve=$sieve, required=$required)"
}
