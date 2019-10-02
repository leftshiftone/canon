package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Overlays
import org.w3c.dom.Node

class OverlaysStrategy : AbstractParseStrategy<Overlays>() {
    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Overlays {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val trigger = node.attrAsText("trigger")

        return Overlays(id, `class`, trigger, factory(node))
    }
}