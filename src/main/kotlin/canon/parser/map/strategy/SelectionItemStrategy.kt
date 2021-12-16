package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.SelectionItem

class SelectionItemStrategy : AbstractParseStrategy<SelectionItem>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): SelectionItem {
        return SelectionItem(
            map["id"]?.toString()?.ifEmpty { null },
            map["class"]?.toString()?.ifEmpty { null },
            map["ariaLabel"]?.toString()?.ifEmpty { null },
            map["name"]?.toString()?.ifEmpty { null },
            factory(map)
        )
    }
}