package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.IVisitor
import canon.support.MapBuilder
import com.fasterxml.jackson.annotation.JsonIgnore

data class Map(@JsonIgnore override val id: String?,
               @JsonIgnore override val `class`: String?,
               val name: String?,
               val src: String?,
               val mapType: String?,
               val centerLng: String?,
               val centerLat: String?,
               val markerIcon: String?,
               val selectedMarkerIcon: String?,
               val routeStartIcon: String?,
               val routeEndIcon: String?,
               val routePoints: String?,
               val centerBrowserLocation: Boolean?,
               val required: Boolean?,
               val zoom: Int?,
               val maxSelections: Int?) : IRenderable, IClassAware {

    override fun <R>accept(visitor: IVisitor<R>, evaluator: IEvaluator):R {
        return visitor.empty()
    }

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String, Any> {
        val builder = MapBuilder()
        builder.put("centerLat", centerLat, 0.0) {evaluator.evaluate(it, context).toDoubleOrNull()?:0.0}
        builder.put("centerLng", centerLng, 0.0) {evaluator.evaluate(it, context).toDoubleOrNull()?:0.0}
        builder.put("name", name) {evaluator.evaluate(it, context)}
        builder.put("src", src) {evaluator.evaluate(it, context)}
        builder.put("mapType", mapType)
        builder.put("markerIcon", markerIcon)
        builder.put("selectedMarkerIcon", selectedMarkerIcon)
        builder.put("routeStartIcon", routeStartIcon)
        builder.put("routeEndIcon", routeEndIcon)
        builder.put("routePoints", routePoints) {evaluator.evaluate(it, context)}
        builder.put("centerBrowserLocation", centerBrowserLocation)
        builder.put("required", required)
        builder.put("zoom", zoom)
        builder.put("maxSelections", maxSelections)

        return builder.toMap().plus(toIdAndClassMap(context, evaluator))
    }

    override fun toString(): String {
        return "Map(name=$name, src=$src, mapType=$mapType, centerLng=$centerLng, centerLat=$centerLat, markerIcon=$markerIcon, selectedMarkerIcon=$selectedMarkerIcon, routeStartIcon=$routeStartIcon, routeEndIcon=$routeEndIcon, routePoints=$routePoints, centerBrowserLocation=$centerBrowserLocation, required=$required, zoom=$zoom, maxSelections=$maxSelections)}"
    }

}
