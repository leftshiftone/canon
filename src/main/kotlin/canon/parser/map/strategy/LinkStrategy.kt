package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Link

class LinkStrategy : AbstractParseStrategy<Link>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Link {
        return Link(map["id"]?.toString()?.ifEmpty { null },
            map["class"]?.toString()?.ifEmpty { null },
            map["ariaLabel"]?.toString()?.ifEmpty { null },
            map["value"]?.toString()?.ifEmpty { null },
            map["text"]?.toString()?.ifEmpty { null })
    }
}