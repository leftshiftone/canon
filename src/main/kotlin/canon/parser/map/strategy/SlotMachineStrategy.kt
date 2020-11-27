package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.SlotMachine

class SlotMachineStrategy : AbstractParseStrategy<SlotMachine>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): SlotMachine {
        return SlotMachine(map["id"]?.toString()?.ifEmpty { null },
                map["class"]?.toString()?.ifEmpty { null },
                map["name"]?.toString()?.ifEmpty { null },
                factory(map))
    }
}