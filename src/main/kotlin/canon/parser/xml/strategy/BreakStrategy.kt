package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Break
import org.w3c.dom.Node

open class BreakStrategy : AbstractParseStrategy<Break>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Break {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val ariaLabel = node.attrAsText("ariaLabel")

        return Break(id, `class`, ariaLabel)
    }
}