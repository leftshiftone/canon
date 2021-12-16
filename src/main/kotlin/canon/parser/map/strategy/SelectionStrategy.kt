package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Selection

class SelectionStrategy : AbstractParseStrategy<Selection>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Selection {
        return Selection(
            map["id"]?.toString()?.ifEmpty { null },
            map["class"]?.toString()?.ifEmpty { null },
            map["ariaLabel"]?.toString()?.ifEmpty { null },
            map["name"]?.toString()?.ifEmpty { null },
            map["countdownInSec"]?.toString()?.ifEmpty { null }?.toInt(),
            factory(map)
        )
    }
}