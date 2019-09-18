package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

class Item(id: String,
           `class`: String,
           @JsonIgnore override val renderables: List<IRenderable>) : AbstractStackeable(id, `class`, renderables)
