package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

class SingleChoice(id: String?,
                   `class`: String?,
                   val name: String?,
                   val sieve: Boolean?,
                   @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(id, `class`, renderables)
