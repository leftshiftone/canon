package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Text

class TextStrategy : AbstractParseStrategy<Text>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Text {
        return Text(map["id"]?.toString()?.ifEmpty { null },
                map["class"]?.toString()?.ifEmpty { null },
                map["regex"]?.toString()?.ifEmpty { null },
                map["placeholder"]?.toString()?.ifEmpty { null },
                map["required"]?.toString()?.ifEmpty { null }?.toBoolean(),
                map["name"]?.toString()?.ifEmpty { null },
                map["value"]?.toString()?.ifEmpty { null })
    }
}