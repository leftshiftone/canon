package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

class Table(id: String?,
            `class`: String?,
            val name: String?,
            @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(id, `class`, renderables)
