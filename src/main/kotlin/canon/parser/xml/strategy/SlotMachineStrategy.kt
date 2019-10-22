package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.SlotMachine
import org.w3c.dom.Node

class SlotMachineStrategy : AbstractParseStrategy<SlotMachine>() {

    override fun parse(node: Node, context: Map<String, Any?>, factory: (Node, Map<String, Any?>) -> List<IRenderable>): SlotMachine {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")

        return SlotMachine(id, `class`, name, factory(node, context))
    }
}