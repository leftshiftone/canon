package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Reel
import org.w3c.dom.Node

class ReelStrategy : AbstractParseStrategy<Reel>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Reel {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")

        return Reel(id, `class`, name, factory(node))
    }
}