package canon.model

import canon.api.IRenderable
import canon.api.IStackeable

class MultipleChoice(id: String,
                     `class`: String,
                     val name: String,
                     val sieve: Boolean,
                     override val renderables: List<IRenderable>) : AbstractRenderable(id, `class`), IStackeable
