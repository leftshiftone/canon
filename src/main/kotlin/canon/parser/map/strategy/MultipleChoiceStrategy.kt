package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.MultipleChoice

class MultipleChoiceStrategy : AbstractParseStrategy<MultipleChoice>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): MultipleChoice {
        return MultipleChoice(map["id"]?.toString(),
                map["class"]?.toString(),
                map["name"]?.toString(),
                map["sieve"] as Boolean?,
                map["required"] as Boolean?,
                factory(map))
    }
}