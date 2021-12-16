package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.SingleChoice

class SingleChoiceStrategy : AbstractParseStrategy<SingleChoice>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): SingleChoice {
        return SingleChoice(
            map["id"]?.toString()?.ifEmpty { null },
            map["class"]?.toString()?.ifEmpty { null },
            map["ariaLabel"]?.toString()?.ifEmpty { null },
            map["name"]?.toString()?.ifEmpty { null },
            map["sieve"]?.toString()?.ifEmpty { null }?.toBoolean(),
            map["required"]?.toString()?.ifEmpty { null }?.toBoolean(),
            factory(map)
        )
    }
}