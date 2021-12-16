package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsText
import canon.model.Phone
import org.w3c.dom.Node

open class PhoneStrategy : AbstractParseStrategy<Phone>() {
    
    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Phone {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val ariaLabel = node.attrAsText("ariaLabel")
        val placeholder = node.attrAsText("placeholder")
        val required = node.attrAsBoolean("required", true)
        val name = node.attrAsText("name")
        val value = node.attrAsText("value")

        return Phone(id, `class`, ariaLabel, placeholder, required, name, value)
    }
}