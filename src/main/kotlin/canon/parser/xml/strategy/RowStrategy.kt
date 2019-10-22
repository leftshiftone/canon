package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Row
import org.w3c.dom.Node

class RowStrategy : AbstractParseStrategy<Row>() {

    override fun parse(node: Node, context: Map<String, Any?>, factory: (Node, Map<String, Any?>) -> List<IRenderable>): Row {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")

        return Row(id, `class`, factory(node, context))
    }
}