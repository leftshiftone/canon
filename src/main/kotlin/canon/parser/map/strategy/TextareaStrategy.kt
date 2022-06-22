package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Textarea

class TextareaStrategy : AbstractParseStrategy<Textarea>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Textarea {
        return Textarea(map["id"]?.toString()?.ifEmpty { null },
            map["class"]?.toString()?.ifEmpty { null },
            map["ariaLabel"]?.toString()?.ifEmpty { null },
            map["placeholder"]?.toString()?.ifEmpty { null },
            map["name"]?.toString()?.ifEmpty { null },
            map["value"]?.toString()?.ifEmpty { null },
            map["required"]?.toString()?.ifEmpty { null }?.toBoolean(),
            map["rows"]?.toString()?.ifEmpty { null }?.toInt(),
            map["cols"]?.toString()?.ifEmpty { null }?.toInt()
        )
    }
}