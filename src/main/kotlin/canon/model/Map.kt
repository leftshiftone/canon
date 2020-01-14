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
               val centerLng: String?,
               val centerLat: String?,
               val markerIcon: String?,
               val selectedMarkerIcon: String?,
               val routePoints: String?,
               val centerBrowserLocation: Boolean?,
               val required: Boolean?,
               val zoom: Int?,
               val maxSelections: Int?) : IRenderable, IClassAware {

    override fun accept(visitor: IVisitor, evaluator: IEvaluator) {
        // do nothing
    }

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        val centerLat = (evaluator.evaluate(centerLat ?: "0.0", context) ?: "0.0").toDoubleOrNull()
        val centerLng = (evaluator.evaluate(centerLng ?: "0.0", context) ?: "0.0").toDoubleOrNull()

        return mapOf("name" to  evaluator.evaluate(name, context),
                "src" to evaluator.evaluate(src ?: "", context),
                "mapType" to mapType,
                "centerLng" to (centerLng ?: 0.0),
                "centerLat" to (centerLat ?: 0.0),
                "markerIcon" to markerIcon,
                "selectedMarkerIcon" to selectedMarkerIcon,
                "routePoints" to evaluator.evaluate(routePoints ?: "", context),
                "centerBrowserLocation" to centerBrowserLocation,
                "required" to required,
                "zoom" to zoom,
                "maxSelections" to maxSelections).plus(toIdAndClassMap(context, evaluator))
    }

    override fun toString(): String {
        return "Map(name=$name, src=$src, mapType=$mapType, centerLng=$centerLng, centerLat=$centerLat, markerIcon=$markerIcon, selectedMarkerIcon=$selectedMarkerIcon, routePoints=$routePoints, centerBrowserLocation=$centerBrowserLocation, required=$required, zoom=$zoom, maxSelections=$maxSelections)}"
    }

}
