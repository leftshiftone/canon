package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.IVisitor
import com.fasterxml.jackson.annotation.JsonIgnore
import kotlin.collections.Map

data class Map(@JsonIgnore override val id: String?,
               @JsonIgnore override val `class`: String?,
               val name: String?,
               val src: String?,
               val mapType: String?,
               val centerLng: Double?,
               val centerLat: Double?,
               val route: String,
               val centerBrowserLocation: Boolean?,
               val required: Boolean?,
               val zoom: Int?,
               val maxSelections: Int?) : IRenderable, IClassAware {

    override fun accept(visitor: IVisitor, evaluator: IEvaluator) {
        // do nothing
    }

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("name" to  evaluator.evaluate(name, context),
                "src" to evaluator.evaluate(src ?: "", context),
                "mapType" to mapType,
                "centerLng" to centerLng,
                "centerLat" to centerLat,
                "route" to evaluator.evaluate(route, context),
                "centerBrowserLocation" to centerBrowserLocation,
                "required" to required,
                "zoom" to zoom,
                "maxSelections" to maxSelections).plus(toIdAndClassMap(context, evaluator))
    }

    override fun toString(): String {
        return "Map(name=$name, src=$src, mapType=$mapType, centerLng=$centerLng, centerLat=$centerLat, route=$route, centerBrowserLocation=$centerBrowserLocation, required=$required, zoom=$zoom, maxSelections=$maxSelections) ${super.toString()}"
    }

}
