package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Textarea

class TextareaStrategy : AbstractParseStrategy<Textarea>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Textarea {
        return Textarea(map["id"]?.toString(),
                map["class"]?.toString(),
                map["placeholder"]?.toString(),
                map["name"]?.toString(),
                map["value"]?.toString(),
                map["required"] as Boolean?,
                map["rows"] as Int?,
                map["cols"] as Int?)
    }
}