package canon.model

import canon.api.IRenderable
import canon.api.IStackeable

class Foreach (id: String,
               `class`: String,
               override val renderables: List<IRenderable>) : AbstractRenderable(id, `class`), IStackeable
