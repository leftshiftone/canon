package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsInt
import canon.extension.attrAsText
import canon.model.Map
import org.w3c.dom.Node

open class MapStrategy : AbstractParseStrategy<Map>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Map {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val name = node.attrAsText("name").ifEmpty { null }
        val src = node.attrAsText("src").ifEmpty { null }
        val mapType = node.attrAsText("mapType", "osm")
        val centerLng = node.attrAsText("centerLng", "0.0")
        val centerLat = node.attrAsText("centerLat", "0.0")
        val markerIcon = node.attrAsText("markerIcon").ifEmpty { null }
        val selectedMarkerIcon = node.attrAsText("selectedMarkerIcon").ifEmpty { null }
        val routeStartIcon = node.attrAsText("routeStartIcon").ifEmpty { null }
        val routeEndIcon = node.attrAsText("routeEndIcon").ifEmpty { null }
        val routePoints = node.attrAsText("routePoints").ifEmpty { null }
        val centerBrowserLocation = node.attrAsBoolean("centerBrowserLocation", false)
        val required = node.attrAsBoolean("required", false)
        val zoom = node.attrAsText("zoom", "8").ifEmpty { null }
        val maxSelections = node.attrAsInt("maxSelections", 3)

        return Map(id, `class`, name, src, mapType, centerLng, centerLat, markerIcon, selectedMarkerIcon, routeStartIcon, routeEndIcon, routePoints,
                centerBrowserLocation, required, zoom, maxSelections)
    }
}