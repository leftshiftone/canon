package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.SlotMachine

class SlotMachineStrategy : AbstractParseStrategy<SlotMachine>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): SlotMachine {
        return SlotMachine(map["id"]?.toString(),
                map["class"]?.toString(),
                map["name"]?.toString(),
                factory(map))
    }
}