package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Trigger

class TriggerStrategy : AbstractParseStrategy<Trigger>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Trigger {
        return Trigger(map["id"]?.toString()?.ifEmpty { null },
            map["class"]?.toString()?.ifEmpty { null },
            map["ariaLabel"]?.toString()?.ifEmpty { null },
            map["name"]?.toString()?.ifEmpty { null },
            map["text"]?.toString()?.ifEmpty { null })
    }
}