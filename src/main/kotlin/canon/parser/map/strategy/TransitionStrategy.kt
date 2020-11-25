package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Transition

class TransitionStrategy : AbstractParseStrategy<Transition>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Transition {
        return Transition(map["id"]?.toString(),
                map["class"]?.toString(),
                map["name"]?.toString(),
                map["direction"]?.toString(),
                map["wrapped"]?.toString(),
                factory(map))
    }
}