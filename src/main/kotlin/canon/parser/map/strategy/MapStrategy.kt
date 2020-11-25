package canon.parser.map.strategy

import canon.api.IRenderable

class MapStrategy : AbstractParseStrategy<canon.model.Map>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): canon.model.Map {
        return canon.model.Map(map["id"]?.toString(),
                map["class"]?.toString(),
                map["name"]?.toString(),
                map["src"]?.toString(),
                map["mapType"]?.toString(),
                map["centerLng"]?.toString(),
                map["centerLat"]?.toString(),
                map["markerIcon"]?.toString(),
                map["selectedMarkerIcon"]?.toString(),
                map["routeStartIcon"]?.toString(),
                map["routeEndIcon"]?.toString(),
                map["routePoints"]?.toString(),
                map["centerBrowserLocation"] as Boolean?,
                map["required"] as Boolean?,
                map["zoom"]?.toString(),
                map["maxSelections"] as Int?)
    }
}