package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Reel

class ReelStrategy : AbstractParseStrategy<Reel>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Reel {
        return Reel(
            map["id"]?.toString()?.ifEmpty { null },
            map["class"]?.toString()?.ifEmpty { null },
            map["ariaLabel"]?.toString()?.ifEmpty { null },
            map["name"]?.toString()?.ifEmpty { null },
            factory(map)
        )
    }
}