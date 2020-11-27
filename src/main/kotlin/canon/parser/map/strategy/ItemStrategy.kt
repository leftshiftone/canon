package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Item

class ItemStrategy : AbstractParseStrategy<Item>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Item {
        return Item(map["id"]?.toString()?.ifEmpty { null },
                map["class"]?.toString()?.ifEmpty { null },
                factory(map))
    }
}