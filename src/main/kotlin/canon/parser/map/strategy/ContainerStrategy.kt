package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Container

class ContainerStrategy : AbstractParseStrategy<Container>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Container {
        return Container(map["id"]?.toString(),
                map["class"]?.toString(),
                map["name"]?.toString(),
                factory(map))
    }
}

