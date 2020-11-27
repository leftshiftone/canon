package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Label

class LabelStrategy : AbstractParseStrategy<Label>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Label {
        return Label(map["id"]?.toString()?.ifEmpty { null },
                map["class"]?.toString()?.ifEmpty { null },
                map["text"]?.toString()?.ifEmpty { null })
    }
}