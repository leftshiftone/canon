package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Link
import org.w3c.dom.Node

open class LinkStrategy : AbstractParseStrategy<Link>() {
    
    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Link {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val value = node.attrAsText("value")

        return Link(id, `class`, value, node.textContent)
    }
}