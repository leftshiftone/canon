package canon.model

import canon.api.IRenderable
import canon.api.IStackeable
import canon.api.IVisitor
import com.fasterxml.jackson.annotation.JsonIgnore

class Camera(id: String,
             `class`: String,
             val name: String,
             val required: Boolean,
             val maxCompressSize: Double,
             @JsonIgnore override val renderables: List<IRenderable>) : AbstractRenderable(id, `class`), IStackeable {

    override fun accept(visitor: IVisitor) {
        renderables.forEach(visitor::visitRenderable)
    }
}
