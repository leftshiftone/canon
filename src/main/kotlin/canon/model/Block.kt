package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Block(@JsonIgnore val id: String?,
            @JsonIgnore val `class`: String?,
            val name: String?,
            @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(renderables) {

    override fun toString() = "Block(name=$name) { ${renderables?.map {it.toString()} }"

}
