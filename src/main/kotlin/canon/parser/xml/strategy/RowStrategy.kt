package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Row
import org.w3c.dom.Node

open class RowStrategy : AbstractParseStrategy<Row>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Row {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }

        return Row(id, `class`, factory(node))
    }
}