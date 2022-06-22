package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Foreach

class ForeachStrategy : AbstractParseStrategy<Foreach>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Foreach {
        return Foreach(
            map["forEachStmt"]?.toString()?.ifEmpty { null },
            map["renderable"] as IRenderable?
        )
    }
}