package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Button
import canon.support.Base64
import org.w3c.dom.Node

class ButtonStrategy : AbstractParseStrategy<Button>() {

    override fun parse(node: Node, context: Map<String, Any?>, factory: (Node, Map<String, Any?>) -> List<IRenderable>): Button {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val text = node.textContent
        val name = node.attrAsText("name")
        val value = node.attrAsText("value")

        return Button(id, `class`, text, name, Base64.encode(mapOf("payload" to value)))
    }
}