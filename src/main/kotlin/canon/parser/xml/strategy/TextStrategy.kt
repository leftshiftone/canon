package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsText
import canon.model.Text
import org.w3c.dom.Node

open class TextStrategy : AbstractParseStrategy<Text>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Text {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val regex = node.attrAsText("regex").ifEmpty { null }
        val placeholder = node.attrAsText("placeholder").ifEmpty { null }
        val required = node.attrAsBoolean("required", true)
        val name = node.attrAsText("name").ifEmpty { null }
        val value = node.attrAsText("value").ifEmpty { null }

        return Text(id, `class`, regex, placeholder, required, name, value)
    }
}