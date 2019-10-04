package canon.model

import canon.api.IRenderable
import canon.api.IStackeable
import canon.api.IVisitor
import com.fasterxml.jackson.annotation.JsonIgnore


class If(@JsonIgnore val expression: String,
         @JsonIgnore var evaluation: Boolean,
         @JsonIgnore val renderable: IRenderable,
         @JsonIgnore override val renderables: List<IRenderable>) : IRenderable, IStackeable  {

    override fun accept(visitor: IVisitor) {
        if (evaluation) visitor.visitRenderable(renderable)
    }



}