package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.SlotMachine
import org.w3c.dom.Node

open class SlotMachineStrategy : AbstractParseStrategy<SlotMachine>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): SlotMachine {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val ariaLabel = node.attrAsText("ariaLabel")
        val name = node.attrAsText("name")

        return SlotMachine(id, `class`, ariaLabel, name, factory(node))
    }
}