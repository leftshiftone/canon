package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Phone(@JsonIgnore val id: String?,
                 @JsonIgnore val `class`: String?,
                 val placeholder: String?,
                 val required: Boolean?,
                 val name: String?,
                 val value: String?) : IRenderable {
    override fun toString() = "Phone(placeholder=$placeholder, required=$required, name=$name, value=$value)"
}
