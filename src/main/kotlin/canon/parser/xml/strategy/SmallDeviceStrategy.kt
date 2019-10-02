package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.SmallDevice
import org.w3c.dom.Node

class SmallDeviceStrategy : AbstractParseStrategy<SmallDevice>() {
    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): SmallDevice {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")

        return SmallDevice(id, `class`, name, factory(node))
    }
}