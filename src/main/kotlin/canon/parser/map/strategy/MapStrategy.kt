package canon.parser.map.strategy

import canon.api.IRenderable

class MapStrategy : AbstractParseStrategy<canon.model.Map>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): canon.model.Map {
        return canon.model.Map(map["id"]?.toString()?.ifEmpty { null },
                map["class"]?.toString()?.ifEmpty { null },
                map["name"]?.toString()?.ifEmpty { null },
                map["src"]?.toString()?.ifEmpty { null },
                map["mapType"]?.toString()?.ifEmpty { null },
                map["centerLng"]?.toString()?.ifEmpty { null },
                map["centerLat"]?.toString()?.ifEmpty { null },
                map["markerIcon"]?.toString()?.ifEmpty { null },
                map["selectedMarkerIcon"]?.toString()?.ifEmpty { null },
                map["routeStartIcon"]?.toString()?.ifEmpty { null },
                map["routeEndIcon"]?.toString()?.ifEmpty { null },
                map["routePoints"]?.toString()?.ifEmpty { null },
                map["centerBrowserLocation"]?.toString()?.ifEmpty { null }?.toBoolean(),
                map["required"]?.toString()?.ifEmpty { null }?.toBoolean(),
                map["zoom"]?.toString()?.ifEmpty { null },
                map["maxSelections"]?.toString()?.ifEmpty { null }?.toInt(),
                map["zoomByRadius"]?.toString()?.ifEmpty { null }
        )
    }
}