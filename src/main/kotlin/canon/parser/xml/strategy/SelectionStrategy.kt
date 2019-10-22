package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Selection
import org.w3c.dom.Node

class SelectionStrategy : AbstractParseStrategy<Selection>() {

    override fun parse(node: Node, context: Map<String, Any?>, factory: (Node, Map<String, Any?>) -> List<IRenderable>): Selection {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")
        val countdownInSec = if (node.attrAsText("countdownInSec").isNullOrEmpty()) 0
        else node.attrAsText("countdownInSec").toInt()

        return Selection(id, `class`, name, countdownInSec, factory(node, context))
    }
}