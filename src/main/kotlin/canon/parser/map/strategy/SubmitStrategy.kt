package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Submit

class SubmitStrategy : AbstractParseStrategy<Submit>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Submit {
        return Submit(map["id"]?.toString()?.ifEmpty { null },
            map["class"]?.toString()?.ifEmpty { null },
            map["ariaLabel"]?.toString()?.ifEmpty { null },
            map["text"]?.toString()?.ifEmpty { null },
            map["name"]?.toString()?.ifEmpty { null })
    }
}