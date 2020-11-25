package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.ReelValue

class ReelValueStrategy : AbstractParseStrategy<ReelValue>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): ReelValue {
        return ReelValue(map["id"]?.toString(),
                map["class"]?.toString(),
                map["value"]?.toString(),
                map["valueType"]?.toString())
    }
}