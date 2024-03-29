package canon.model

import canon.api.*
import canon.support.MapBuilder
import com.fasterxml.jackson.annotation.JsonIgnore

data class Map(
    @JsonIgnore override val id: String?,
    @JsonIgnore override val `class`: String?,
    @JsonIgnore override val ariaLabel: String?,
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
    val zoom: String?,
    val maxSelections: Int?,
    val zoomByRadius: String?
) : IRenderable, IClassAware {

    override fun <R> accept(visitor: IVisitor<R>, evaluator: IEvaluator): R {
        return visitor.empty()
    }

    override fun toMap(context: KMap<String, Any>, evaluator: IEvaluator): KMap<String, Any> {
        val builder = MapBuilder()
        builder.put("centerLat", centerLat, 0.0) { evaluator.evaluate(it, context).toDoubleOrNull() ?: 0.0 }
        builder.put("centerLng", centerLng, 0.0) { evaluator.evaluate(it, context).toDoubleOrNull() ?: 0.0 }
        builder.put("name", name) { evaluator.evaluate(it, context) }
        builder.put("src", src) { evaluator.evaluate(it, context) }
        builder.put("mapType", mapType)
        builder.put("markerIcon", markerIcon)
        builder.put("selectedMarkerIcon", selectedMarkerIcon)
        builder.put("routeStartIcon", routeStartIcon)
        builder.put("routeEndIcon", routeEndIcon)
        builder.put("routePoints", routePoints) { evaluator.evaluate(it, context) }
        builder.put("centerBrowserLocation", centerBrowserLocation)
        builder.put("required", required)
        builder.put("zoom", zoom, 8) { evaluator.evaluate(it, context).toIntOrNull() ?: 8 }
        builder.put("maxSelections", maxSelections)
        builder.put("zoomByRadius", zoomByRadius, -1) { evaluator.evaluate(it, context).toIntOrNull() ?: -1 }

        return builder.toMap().plus(toIdAndClassAndAriaLabelMap(context, evaluator))
    }

    override fun toString(): String {
        return "Map(name=$name, src=$src, mapType=$mapType, centerLng=$centerLng, centerLat=$centerLat, markerIcon=$markerIcon, selectedMarkerIcon=$selectedMarkerIcon, routeStartIcon=$routeStartIcon, routeEndIcon=$routeEndIcon, routePoints=$routePoints, centerBrowserLocation=$centerBrowserLocation, required=$required, zoom=$zoom, maxSelections=$maxSelections, zoomByRadius=$zoomByRadius)}"
    }

}
