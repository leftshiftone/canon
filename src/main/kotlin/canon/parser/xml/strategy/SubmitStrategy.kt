package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Submit
import org.w3c.dom.Node

class SubmitStrategy : AbstractParseStrategy<Submit>() {
    
    override fun parse(node: Node, context: Map<String, Any?>, factory: (Node, Map<String, Any?>) -> List<IRenderable>): Submit {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")

        return Submit(id, `class`, node.textContent, name)
    }
}