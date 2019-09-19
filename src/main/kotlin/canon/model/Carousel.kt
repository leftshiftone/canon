package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

class Carousel(id: String,
               `class`: String,
               val text: String,
               val name: String,
               val selected: Boolean,
               @JsonIgnore override val renderables: List<IRenderable>) : AbstractStackeable(id, `class`, renderables)
