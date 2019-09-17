package canon.model

import canon.api.IRenderable
import canon.api.IStackeable
import canon.api.IVisitor

class Selection(id: String,
                `class`: String,
                val name: String,
                val countdownInSec: Int,
                override val renderables: List<IRenderable>) : AbstractRenderable(id, `class`), IStackeable {

    override fun accept(visitor: IVisitor) {
        renderables.forEach(visitor::visitRenderable)
    }
}
