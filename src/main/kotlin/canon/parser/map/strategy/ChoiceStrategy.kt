package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Choice

class ChoiceStrategy : AbstractParseStrategy<Choice>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Choice {
        return Choice(
            map["id"]?.toString()?.ifEmpty { null },
            map["class"]?.toString()?.ifEmpty { null },
            map["ariaLabel"]?.toString()?.ifEmpty { null },
            map["name"]?.toString()?.ifEmpty { null },
            map["text"]?.toString()?.ifEmpty { null },
            map["selected"]?.toString()?.ifEmpty { null },
            factory(map)
        )
    }
}