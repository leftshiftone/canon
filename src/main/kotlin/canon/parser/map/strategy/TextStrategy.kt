package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Text

class TextStrategy : AbstractParseStrategy<Text>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Text {
        return Text(map["id"]?.toString(),
                map["class"]?.toString(),
                map["regex"]?.toString(),
                map["placeholder"]?.toString(),
                map["required"] as Boolean?,
                map["name"]?.toString(),
                map["value"]?.toString())
    }
}