package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Bold
import org.w3c.dom.Node

open class BoldStrategy : AbstractParseStrategy<Bold>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Bold {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val ariaLabel = node.attrAsText("ariaLabel")

        return Bold(id, `class`, ariaLabel, node.textContent)
    }

}
