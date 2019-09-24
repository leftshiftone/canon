package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

class Selection(id: String,
                `class`: String,
                val name: String,
                val countdownInSec: Int,
                @JsonIgnore override val renderables: List<IRenderable>) : AbstractStackeable(id, `class`, renderables)
