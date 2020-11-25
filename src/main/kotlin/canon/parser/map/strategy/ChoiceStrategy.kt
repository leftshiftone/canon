package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Choice

class ChoiceStrategy : AbstractParseStrategy<Choice>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Choice {
        return Choice(map["id"]?.toString(),
                map["class"]?.toString(),
                map["name"]?.toString(),
                map["text"]?.toString(),
                map["selected"]?.toString(),
                factory(map))
    }
}