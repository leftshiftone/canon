package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.SlotMachine
import org.w3c.dom.Node

open class SlotMachineStrategy : AbstractParseStrategy<SlotMachine>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): SlotMachine {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val name = node.attrAsText("name").ifEmpty { null }

        return SlotMachine(id, `class`, name, factory(node))
    }
}