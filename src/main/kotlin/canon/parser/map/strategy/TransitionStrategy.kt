package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Transition

class TransitionStrategy : AbstractParseStrategy<Transition>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Transition {
        return Transition(
            map["id"]?.toString()?.ifEmpty { null },
            map["class"]?.toString()?.ifEmpty { null },
            map["ariaLabel"]?.toString()?.ifEmpty { null },
            map["name"]?.toString()?.ifEmpty { null },
            map["direction"]?.toString()?.ifEmpty { null },
            map["wrapped"]?.toString()?.ifEmpty { null },
            factory(map)
        )
    }
}