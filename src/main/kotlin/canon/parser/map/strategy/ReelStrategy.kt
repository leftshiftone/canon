package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Reel

class ReelStrategy : AbstractParseStrategy<Reel>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Reel {
        return Reel(map["id"]?.toString(),
                map["class"]?.toString(),
                map["name"]?.toString(),
                factory(map))
    }
}