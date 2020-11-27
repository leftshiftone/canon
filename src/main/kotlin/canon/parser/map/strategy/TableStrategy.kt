package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Table

class TableStrategy : AbstractParseStrategy<Table>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Table {
        return Table(map["id"]?.toString()?.ifEmpty { null },
                map["class"]?.toString()?.ifEmpty { null },
                map["name"]?.toString()?.ifEmpty { null },
                factory(map))
    }
}