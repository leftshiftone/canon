package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Overlays

class OverlaysStrategy : AbstractParseStrategy<Overlays>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Overlays {
        return Overlays(map["id"]?.toString(),
                map["class"]?.toString(),
                map["trigger"]?.toString(),
                factory(map))
    }
}