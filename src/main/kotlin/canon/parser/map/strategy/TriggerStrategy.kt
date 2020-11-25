package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Trigger

class TriggerStrategy : AbstractParseStrategy<Trigger>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Trigger {
        return Trigger(map["id"]?.toString(),
                map["class"]?.toString(),
                map["name"]?.toString(),
                map["text"]?.toString())
    }
}