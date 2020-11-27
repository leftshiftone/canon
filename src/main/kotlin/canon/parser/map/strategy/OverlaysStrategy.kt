package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Overlays

class OverlaysStrategy : AbstractParseStrategy<Overlays>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Overlays {
        return Overlays(map["id"]?.toString()?.ifEmpty { null },
                map["class"]?.toString()?.ifEmpty { null },
                map["trigger"]?.toString()?.ifEmpty { null },
                factory(map))
    }
}