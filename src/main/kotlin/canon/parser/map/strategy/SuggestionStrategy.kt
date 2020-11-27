package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Suggestion

class SuggestionStrategy : AbstractParseStrategy<Suggestion>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Suggestion {
        return Suggestion(map["id"]?.toString()?.ifEmpty { null },
                map["class"]?.toString()?.ifEmpty { null },
                map["text"]?.toString()?.ifEmpty { null },
                map["name"]?.toString()?.ifEmpty { null },
                map["value"]?.toString()?.ifEmpty { null })
    }
}