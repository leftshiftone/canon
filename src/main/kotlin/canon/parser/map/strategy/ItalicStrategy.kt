package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Italic

class ItalicStrategy : AbstractParseStrategy<Italic>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Italic {
        return Italic(map["id"]?.toString()?.ifEmpty { null },
                map["class"]?.toString()?.ifEmpty { null },
                map["text"]?.toString()?.ifEmpty { null })
    }
}