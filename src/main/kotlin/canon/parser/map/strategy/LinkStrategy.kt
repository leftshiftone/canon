package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Link

class LinkStrategy : AbstractParseStrategy<Link>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Link {
        return Link(map["id"]?.toString(),
                map["class"]?.toString(),
                map["value"]?.toString(),
                map["text"]?.toString())
    }
}