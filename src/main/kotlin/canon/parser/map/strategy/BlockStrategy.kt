package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Block

class BlockStrategy : AbstractParseStrategy<Block>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Block {
        return Block(map["id"]?.toString(),
                map["class"]?.toString(),
                map["name"]?.toString(),
                factory(map))
    }
}