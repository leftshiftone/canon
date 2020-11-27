package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Basket

class BasketStrategy : AbstractParseStrategy<Basket>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Basket {
        return Basket(map["id"]?.toString()?.ifEmpty { null },
                map["class"]?.toString()?.ifEmpty { null },
                map["name"]?.toString()?.ifEmpty { null },
                map["required"]?.toString()?.ifEmpty { null }?.toBoolean(),
                factory(map))
    }
}