package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Selectable

class SelectableStrategy : AbstractParseStrategy<Selectable>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Selectable {
        return Selectable(map["id"]?.toString(),
                map["class"]?.toString(),
                map["name"]?.toString(),
                factory(map))
    }
}