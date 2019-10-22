package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Label
import org.w3c.dom.Node

class LabelStrategy : AbstractParseStrategy<Label>() {

    override fun parse(node: Node, context: Map<String, Any?>, factory: (Node, Map<String, Any?>) -> List<IRenderable>): Label {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")

        return Label(id, `class`, node.textContent)
    }

}
