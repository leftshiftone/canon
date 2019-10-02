package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

class Camera(id: String,
             `class`: String,
             val name: String,
             val required: Boolean,
             val maxCompressSize: Double,
             @JsonIgnore override val renderables: List<IRenderable>) : AbstractStackeable(id, `class`, renderables)
