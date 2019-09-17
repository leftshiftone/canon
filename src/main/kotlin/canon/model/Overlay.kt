package canon.model

import canon.api.IRenderable
import canon.api.IStackeable

class Overlay (id: String, `class`: String,
               val trigger: String,
               override val renderables: List<IRenderable>) : AbstractRenderable(id, `class`), IStackeable