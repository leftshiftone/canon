package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

class Items(id: String,
            `class`: String,
            val ordered : Boolean,
            @JsonIgnore override val renderables: List<IRenderable>) : AbstractStackeable(id, `class`, renderables)
