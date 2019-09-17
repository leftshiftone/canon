package canon.model

import canon.api.IRenderable
import canon.api.IStackeable
import canon.api.IVisitor

class Carousel(id: String,
               `class`: String,
               val text: String,
               val name: String,
               val selected: Boolean,
               override val renderables: List<IRenderable>) : AbstractRenderable(id, `class`), IStackeable {

    override fun accept(visitor: IVisitor) {
        renderables.forEach(visitor::visitRenderable)
    }
}
