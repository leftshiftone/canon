package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class SlotMachine(@JsonIgnore val id: String?,
                  @JsonIgnore val `class`: String?,
                  val name: String?,
                  @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(renderables) {
    override fun toString() = "SlotMachine(name=$name)"
}
