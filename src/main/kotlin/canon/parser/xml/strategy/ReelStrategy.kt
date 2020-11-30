package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Reel
import org.w3c.dom.Node

open class ReelStrategy : AbstractParseStrategy<Reel>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Reel {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val name = node.attrAsText("name").ifEmpty { null }

        return Reel(id, `class`, name, factory(node))
    }
}