package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Col
import org.w3c.dom.Node

class ColStrategy : AbstractParseStrategy<Col>() {

    override fun parse(node: Node, context: Map<String, Any?>, factory: (Node, Map<String, Any?>) -> List<IRenderable>): Col {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")

        return Col(id, `class`, factory(node, context))
    }


}