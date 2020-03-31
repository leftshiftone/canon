package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsText
import canon.model.Text
import org.w3c.dom.Node

open class TextStrategy : AbstractParseStrategy<Text>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Text {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val regex = node.attrAsText("regex")
        val placeholder = node.attrAsText("placeholder")
        val required = node.attrAsBoolean("required", true)
        val name = node.attrAsText("name")
        val value = node.attrAsText("value")

        return Text(id, `class`, regex, placeholder, required, name, value)
    }
}