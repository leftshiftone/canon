package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Break

class BreakStrategy : AbstractParseStrategy<Break>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Break {
        return Break(map["id"]?.toString()?.ifEmpty { null },
                map["class"]?.toString()?.ifEmpty { null })
    }
}