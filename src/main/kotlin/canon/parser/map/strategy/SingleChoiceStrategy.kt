package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.SingleChoice

class SingleChoiceStrategy : AbstractParseStrategy<SingleChoice>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): SingleChoice {
        return SingleChoice(map["id"]?.toString(),
                map["class"]?.toString(),
                map["name"]?.toString(),
                map["sieve"] as Boolean?,
                map["required"] as Boolean?,
                factory(map))
    }
}