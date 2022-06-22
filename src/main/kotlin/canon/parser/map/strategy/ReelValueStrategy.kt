package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.ReelValue

class ReelValueStrategy : AbstractParseStrategy<ReelValue>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): ReelValue {
        return ReelValue(map["id"]?.toString()?.ifEmpty { null },
            map["class"]?.toString()?.ifEmpty { null },
            map["ariaLabel"]?.toString()?.ifEmpty { null },
            map["value"]?.toString()?.ifEmpty { null },
            map["valueType"]?.toString()?.ifEmpty { null })
    }
}