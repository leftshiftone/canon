package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Block
import org.w3c.dom.Node

open class BlockStrategy : AbstractParseStrategy<Block>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Block {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")

        return Block(id, `class`, name, factory(node))
    }
}