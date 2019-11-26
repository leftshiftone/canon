package canon.model

import canon.api.IClassAware

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Block(@JsonIgnore override val id: String?,
                 @JsonIgnore override val `class`: String?,
                 val name: String?,
                 @JsonIgnore val renderables: List<IRenderable>?) : AbstractStackeable(renderables), IClassAware {

    override fun toString() = "Block(name=$name) { ${renderables?.map { it.toString() }}"

}
