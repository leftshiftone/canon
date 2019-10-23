package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Overlay(@JsonIgnore val id: String?, @JsonIgnore val `class`: String?,
              val trigger: String?,
              @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(renderables) {
    override fun toString() = "Overlay(trigger=$trigger)"
}
