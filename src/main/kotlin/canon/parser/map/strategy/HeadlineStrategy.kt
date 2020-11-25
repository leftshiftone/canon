package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Headline

class HeadlineStrategy : AbstractParseStrategy<Headline>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Headline {
        return Headline(map["id"]?.toString(),
                map["class"]?.toString(),
                map["text"]?.toString())
    }
}