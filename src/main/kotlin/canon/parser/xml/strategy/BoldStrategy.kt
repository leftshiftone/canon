package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Bold
import org.w3c.dom.Node

class BoldStrategy : AbstractParseStrategy<Bold>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Bold {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")

        return Bold(id, `class`, node.textContent)
    }

}
