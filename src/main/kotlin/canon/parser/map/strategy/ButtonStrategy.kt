package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Button

class ButtonStrategy : AbstractParseStrategy<Button>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Button {
        return Button(
            map["id"]?.toString()?.ifEmpty { null },
            map["class"]?.toString()?.ifEmpty { null },
            map["ariaLabel"]?.toString()?.ifEmpty { null },
            map["text"]?.toString()?.ifEmpty { null },
            map["name"]?.toString()?.ifEmpty { null },
            map["value"]?.toString()?.ifEmpty { null },
            factory(map)
        )
    }
}