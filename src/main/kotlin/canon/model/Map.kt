package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.IVisitor

class Map(id: String?,
          `class`: String?,
          val name:String?,
          val src: String?,
          val mapType:String?,
          val centerLng: String?,
          val centerLat: String?,
          val exact:Boolean?,
          val centerBrowserLocation:Boolean?,
          val required : Boolean?) : AbstractRenderable(id, `class`), IRenderable {

    override fun accept(visitor: IVisitor, evaluator: IEvaluator) {
        // do nothing
    }
}
