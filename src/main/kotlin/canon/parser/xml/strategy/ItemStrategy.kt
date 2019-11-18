package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Item
import org.w3c.dom.Node

class ItemStrategy : AbstractParseStrategy<Item>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Item {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")

        return Item(id, `class`, factory(node))
    }
}