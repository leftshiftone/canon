package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsInt
import canon.extension.attrAsText
import canon.model.Textarea
import org.w3c.dom.Node

class TextareaStrategy : AbstractParseStrategy<Textarea>() {
    
    override fun parse(node: Node, context: Map<String, Any?>, factory: (Node, Map<String, Any?>) -> List<IRenderable>): Textarea {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val placeholder = node.attrAsText("placeholder")
        val name = node.attrAsText("name")
        val value = node.attrAsText("value")
        val required = node.attrAsBoolean("required", false)
        val rows = node.attrAsInt("rows", 0)
        val cols = node.attrAsInt("cols", 0)

        return Textarea(id, `class`, placeholder, name, value, required, rows, cols)
    }
}