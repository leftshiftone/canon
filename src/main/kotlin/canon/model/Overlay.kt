package canon.model

import canon.api.IRenderable
import canon.api.IStackeable
import canon.api.IVisitor

class Overlay (id: String, `class`: String,
               val trigger: String,
               override val renderables: List<IRenderable>) : AbstractRenderable(id, `class`), IStackeable {

    override fun accept(visitor: IVisitor) {
        renderables.forEach(visitor::visitRenderable)
    }
}