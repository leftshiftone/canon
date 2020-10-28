package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Basket

class BasketStrategy : AbstractParseStrategy<Basket>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Basket {
        return Basket(map["id"]?.toString(),
                map["class"]?.toString(),
                map["name"]?.toString(),
                map["required"]?.toString()?.toBoolean(),
                factory(map))
    }
}