package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Suggestion

class SuggestionStrategy : AbstractParseStrategy<Suggestion>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Suggestion {
        return Suggestion(map["id"]?.toString(),
                map["class"]?.toString(),
                map["text"]?.toString(),
                map["name"]?.toString(),
                map["value"]?.toString())
    }
}