package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsInt
import canon.extension.attrAsText
import canon.model.Textarea
import org.w3c.dom.Node

open class TextareaStrategy : AbstractParseStrategy<Textarea>() {
    
    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Textarea {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val placeholder = node.attrAsText("placeholder").ifEmpty { null }
        val name = node.attrAsText("name").ifEmpty { null }
        val value = node.attrAsText("value").ifEmpty { null }
        val required = node.attrAsBoolean("required", false)
        val rows = node.attrAsInt("rows", 10)
        val cols = node.attrAsInt("cols", 40)

        return Textarea(id, `class`, placeholder, name, value, required, rows, cols)
    }
}