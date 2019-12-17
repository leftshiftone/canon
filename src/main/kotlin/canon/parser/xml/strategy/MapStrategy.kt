package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsDouble
import canon.extension.attrAsInt
import canon.extension.attrAsText
import canon.model.Map
import org.apache.commons.lang3.StringUtils
import org.w3c.dom.Node

open class MapStrategy : AbstractParseStrategy<Map>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Map {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")
        val src = node.attrAsText("src")
        var mapType = node.attrAsText("mapType")
        if (StringUtils.isEmpty(mapType)) mapType = "osm"
        val centerLng = node.attrAsDouble("centerLng", 0.0)
        val centerLat = node.attrAsDouble("centerLat", 0.0)
        val exact = node.attrAsBoolean("exact", false)
        val centerBrowserLocation = node.attrAsBoolean("centerBrowserLocation", false)
        val required = node.attrAsBoolean("required", false)
        val zoom = node.attrAsInt("zoom", 8)
        val maxSelections = node.attrAsInt("maxSelections", 3)

        return Map(id, `class`, name, src, mapType, centerLng, centerLat, exact, centerBrowserLocation, required, zoom, maxSelections)
    }
}