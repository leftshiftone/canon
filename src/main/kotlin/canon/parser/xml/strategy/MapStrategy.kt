package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsText
import canon.model.Map
import org.apache.commons.lang3.StringUtils
import org.w3c.dom.Node

class MapStrategy : AbstractParseStrategy<Map>() {
    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Map {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")
        val src = node.attrAsText("src")
        var mapType = node.attrAsText("mapType")
        if (StringUtils.isEmpty(mapType) || StringUtils.equals(mapType, "google") || StringUtils.equals(mapType, "osm")) mapType = "osm"
        val centerLng = node.attrAsText("centerLng")
        val centerLat = node.attrAsText("centerLat")
        val exact = node.attrAsBoolean("exact", false)
        val centerBrowserLocation = node.attrAsBoolean("centerBrowserLocation", false)
        val required = node.attrAsBoolean("required", false)

        return Map(id, `class`, name, src, mapType, centerLng, centerLat, exact, centerBrowserLocation, required)
    }
}