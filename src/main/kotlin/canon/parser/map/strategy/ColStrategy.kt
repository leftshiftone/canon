package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Col

class ColStrategy : AbstractParseStrategy<Col>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Col {
        return Col(map["id"]?.toString(),
                map["class"]?.toString(),
                factory(map))
    }
}