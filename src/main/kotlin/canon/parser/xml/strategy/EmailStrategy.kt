package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsText
import canon.model.Email
import org.w3c.dom.Node

open class EmailStrategy : AbstractParseStrategy<Email>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Email {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val ariaLabel = node.attrAsText("ariaLabel")
        val placeholder = node.attrAsText("placeholder")
        val required = node.attrAsBoolean("required", false)
        val name = node.attrAsText("name")
        val value = node.attrAsText("value")

        return Email(id, `class`, ariaLabel, placeholder, required, name, value)
    }

}
