package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

class Choice(id: String,
             `class`: String,
             val text : String,
             val selected : String,
             @JsonIgnore override val renderables: List<IRenderable>) : AbstractStackeable(id, `class`, renderables)
