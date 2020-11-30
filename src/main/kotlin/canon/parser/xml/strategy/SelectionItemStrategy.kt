package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.SelectionItem
import org.w3c.dom.Node

open class SelectionItemStrategy : AbstractParseStrategy<SelectionItem>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): SelectionItem {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val name = node.attrAsText("name").ifEmpty { null }

        return SelectionItem(id, `class`, name, factory(node))
    }
}