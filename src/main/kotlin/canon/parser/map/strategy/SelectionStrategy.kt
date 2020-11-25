package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Selection

class SelectionStrategy : AbstractParseStrategy<Selection>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Selection {
        return Selection(map["id"]?.toString(),
                map["class"]?.toString(),
                map["name"]?.toString(),
                map["countdownInSec"] as Int?,
                factory(map))
    }
}