package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Overlay

class OverlayStrategy : AbstractParseStrategy<Overlay>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Overlay {
        return Overlay(map["id"]?.toString(),
                map["class"]?.toString(),
                map["trigger"]?.toString(),
                factory(map))
    }
}