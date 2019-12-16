package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsText
import canon.model.NewText
import org.w3c.dom.Node

open class TextStrategy : AbstractParseStrategy<NewText>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): NewText {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val regex = node.attrAsText("regex")
        val placeholder = node.attrAsText("placeholder")
        val required = node.attrAsBoolean("required", true)
        val name = node.attrAsText("name")

        return NewText(id, `class`, regex, placeholder, required, name, node.textContent)
    }

}
