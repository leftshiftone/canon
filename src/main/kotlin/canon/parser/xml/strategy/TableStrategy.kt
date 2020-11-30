package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Table
import org.w3c.dom.Node

open class TableStrategy : AbstractParseStrategy<Table>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Table {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val name = node.attrAsText("name").ifEmpty { null }

        return Table(id, `class`, name, factory(node))
    }
}