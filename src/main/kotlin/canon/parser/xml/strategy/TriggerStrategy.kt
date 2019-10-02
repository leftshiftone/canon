package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Trigger
import org.w3c.dom.Node

class TriggerStrategy : AbstractParseStrategy<Trigger>() {
    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Trigger {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")
        val text = node.attrAsText("text")

        return Trigger(id, `class`, name, text)
    }
}