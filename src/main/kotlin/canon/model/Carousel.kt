package canon.model

import canon.api.IRenderable
import canon.api.IStackeable
import canon.api.IVisitor
import com.fasterxml.jackson.annotation.JsonIgnore

class Carousel(id: String,
               `class`: String,
               val text: String,
               val name: String,
               val selected: Boolean,
               @JsonIgnore override val renderables: List<IRenderable>) : AbstractRenderable(id, `class`), IStackeable {

    override fun accept(visitor: IVisitor) {
        renderables.forEach(visitor::visitRenderable)
    }
}
