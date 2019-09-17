package canon.model

import canon.api.IRenderable
import canon.api.IStackeable

class Camera(id: String,
             `class`: String,
             val name: String,
             val required: Boolean,
             val maxCompressSize: Double,
             override val renderables: List<IRenderable>) : AbstractRenderable(id, `class`), IStackeable
