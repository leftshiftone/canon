package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Form(@JsonIgnore val id: String?,
           @JsonIgnore val `class`: String?,
           val name: String?,
           @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(renderables) {

    override fun toString() = "Form(name=$name)"
}
