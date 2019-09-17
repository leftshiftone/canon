package canon.model

import canon.api.IRenderable
import canon.api.IStackeable

class Choice(id: String,
             `class`: String,
             `text` : String,
             `selected` : String,
             override val renderables: List<IRenderable>) : AbstractRenderable(id, `class`), IStackeable
