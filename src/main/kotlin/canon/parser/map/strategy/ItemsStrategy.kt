package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Items

class ItemsStrategy : AbstractParseStrategy<Items>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Items {
        return Items(map["id"]?.toString(),
                map["class"]?.toString(),
                map["ordered"] as Boolean?,
                factory(map))
    }
}