package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.SelectionItem
import org.w3c.dom.Node

open class SelectionItemStrategy : AbstractParseStrategy<SelectionItem>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): SelectionItem {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val ariaLabel = node.attrAsText("ariaLabel")
        val name = node.attrAsText("name")

        return SelectionItem(id, `class`, ariaLabel, name, factory(node))
    }
}