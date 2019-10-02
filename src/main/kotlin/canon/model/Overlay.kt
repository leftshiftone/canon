package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

class Overlay (id: String, `class`: String,
               val trigger: String,
               @JsonIgnore override val renderables: List<IRenderable>) : AbstractStackeable(id, `class`, renderables)
