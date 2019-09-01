package canon.model

import canon.api.IRenderable
import canon.api.IStackeable

class Table(id: String,
            `class`: String,
            val name: String,
            override val renderables: List<IRenderable>) : AbstractRenderable(id, `class`), IStackeable
