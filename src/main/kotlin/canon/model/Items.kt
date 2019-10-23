package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Items(@JsonIgnore val id: String?,
            @JsonIgnore val `class`: String?,
            val ordered: Boolean?,
            @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(renderables) {

    override fun toString() = "Items(ordered=$ordered) { ${renderables?.map { it.toString() }} }"

}
