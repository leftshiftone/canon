package canon.model

import canon.api.IClassAware
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Form(@JsonIgnore override val id: String?,
           @JsonIgnore override val `class`: String?,
           val name: String?,
           @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(renderables), IClassAware {

    override fun toString() = "Form(name=$name) { ${renderables?.map { it.toString() }} }"
}
