package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

class Transition (id: String,
                  `class`: String,
                  val name: String,
                  val direction: String,
                  val wrapped: String,
                  @JsonIgnore override val renderables: List<IRenderable>) : AbstractStackeable(id, `class`, renderables)
