package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.IVisitor
import com.fasterxml.jackson.annotation.JsonIgnore

data class Map(@JsonIgnore override val id: String?,
               @JsonIgnore override val `class`: String?,
               val name: String?,
               val src: String?,
               val mapType: String?,
               val centerLng: Double?,
               val centerLat: Double?,
               val exact: Boolean?,
               val centerBrowserLocation: Boolean?,
               val required: Boolean?,
               val zoom: Int?,
               val maxSelections: Int?) : IRenderable, IClassAware {

    override fun accept(visitor: IVisitor, evaluator: IEvaluator) {
        // do nothing
    }

    override fun toString(): String {
        return "Map(name=$name, src=$src, mapType=$mapType, centerLng=$centerLng, centerLat=$centerLat, exact=$exact, centerBrowserLocation=$centerBrowserLocation, required=$required, zoom=$zoom, maxSelections=$maxSelections) ${super.toString()}"
    }

}
