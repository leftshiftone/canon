package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Table
import org.w3c.dom.Node

open class TableStrategy : AbstractParseStrategy<Table>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Table {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val ariaLabel = node.attrAsText("ariaLabel")
        val name = node.attrAsText("name")

        return Table(id, `class`, ariaLabel, name, factory(node))
    }
}