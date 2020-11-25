package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Button

class ButtonStrategy : AbstractParseStrategy<Button>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Button {
        return Button(map["id"]?.toString(),
                map["class"]?.toString(),
                map["text"]?.toString(),
                map["name"]?.toString(),
                map["value"]?.toString(),
                factory(map))
    }
}