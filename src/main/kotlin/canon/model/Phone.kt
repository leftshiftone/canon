package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Phone(@JsonIgnore override val id: String?,
                 @JsonIgnore override val `class`: String?,
                 val placeholder: String?,
                 val required: Boolean?,
                 val name: String?,
                 val value: String?) : IRenderable, IClassAware {
    override fun toString() = "Phone(placeholder=$placeholder, required=$required, name=$name, value=$value)"
}
