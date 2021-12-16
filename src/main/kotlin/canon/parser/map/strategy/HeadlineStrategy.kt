package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Headline

class HeadlineStrategy : AbstractParseStrategy<Headline>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Headline {
        return Headline(map["id"]?.toString()?.ifEmpty { null },
            map["class"]?.toString()?.ifEmpty { null },
            map["ariaLabel"]?.toString()?.ifEmpty { null },
            map["text"]?.toString()?.ifEmpty { null })
    }
}