package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Submit

class SubmitStrategy : AbstractParseStrategy<Submit>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Submit {
        return Submit(map["id"]?.toString(),
                map["class"]?.toString(),
                map["text"]?.toString(),
                map["name"]?.toString())
    }
}