package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.SelectionItem

class SelectionItemStrategy : AbstractParseStrategy<SelectionItem>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): SelectionItem {
        return SelectionItem(map["id"]?.toString(),
                map["class"]?.toString(),
                map["name"]?.toString(),
                factory(map))
    }
}