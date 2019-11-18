package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Overlay
import org.w3c.dom.Node

class OverlayStrategy : AbstractParseStrategy<Overlay>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Overlay {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val trigger = node.attrAsText("trigger")

        return Overlay(id, `class`, trigger, factory(node))
    }
}