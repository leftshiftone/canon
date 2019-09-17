package canon.model

import canon.api.IRenderable
import canon.api.IStackeable

class Items(id: String,
            `class`: String,
            `ordered` : Boolean,
            override val renderables: List<IRenderable>) : AbstractRenderable(id, `class`), IStackeable
