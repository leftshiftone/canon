package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.If

class IfStrategy : AbstractParseStrategy<If>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): If {
        return If(map["expression"]?.toString()?.ifEmpty { null },
                map["renderable"] as IRenderable?)
    }
}