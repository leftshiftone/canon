package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Row

class RowStrategy : AbstractParseStrategy<Row>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Row {
        return Row(map["id"]?.toString()?.ifEmpty { null },
                map["class"]?.toString()?.ifEmpty { null },
                factory(map))
    }
}