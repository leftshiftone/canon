package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

class Row(id: String?,
          `class`: String?,
          @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(id, `class`, renderables) {

    override fun toString(): String {
        return "Row() ${super.toString()}"
    }
}
