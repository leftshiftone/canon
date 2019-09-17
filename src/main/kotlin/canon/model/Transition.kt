package canon.model

import canon.api.IRenderable
import canon.api.IStackeable

class Transition (id: String,
                  `class`: String,
                  val name: String,
                  val direction: String,
                  val wrapped: String,
                  override val renderables: List<IRenderable>) : AbstractRenderable(id, `class`), IStackeable
