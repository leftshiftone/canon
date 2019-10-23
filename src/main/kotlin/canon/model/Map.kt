package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.IVisitor
import com.fasterxml.jackson.annotation.JsonIgnore

data class Map(@JsonIgnore val id: String?,
               @JsonIgnore val `class`: String?,
               val name: String?,
               val src: String?,
               val mapType: String?,
               val centerLng: String?,
               val centerLat: String?,
               val exact: Boolean?,
               val centerBrowserLocation: Boolean?,
               val required: Boolean?,
               val maxSelections: Int?) : IRenderable {

    override fun accept(visitor: IVisitor, evaluator: IEvaluator) {
        // do nothing
    }

    override fun toString(): String {
        return "Map(name=$name, src=$src, mapType=$mapType, centerLng=$centerLng, centerLat=$centerLat, exact=$exact, centerBrowserLocation=$centerBrowserLocation, required=$required, maxSelections=$maxSelections) ${super.toString()}"
    }

}
