package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Selectable

class SelectableStrategy : AbstractParseStrategy<Selectable>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Selectable {
        return Selectable(
            map["id"]?.toString()?.ifEmpty { null },
            map["class"]?.toString()?.ifEmpty { null },
            map["ariaLabel"]?.toString()?.ifEmpty { null },
            map["name"]?.toString()?.ifEmpty { null },
            factory(map)
        )
    }
}