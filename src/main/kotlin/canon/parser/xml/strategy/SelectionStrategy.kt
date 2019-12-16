package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Selection
import org.w3c.dom.Node

open class SelectionStrategy : AbstractParseStrategy<Selection>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Selection {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")
        val countdownInSec = if (node.attrAsText("countdownInSec").isEmpty()) 0
        else node.attrAsText("countdownInSec").toInt()

        return Selection(id, `class`, name, countdownInSec, factory(node))
    }
}