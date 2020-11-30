package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsText
import canon.model.Phone
import org.w3c.dom.Node

open class PhoneStrategy : AbstractParseStrategy<Phone>() {
    
    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Phone {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val placeholder = node.attrAsText("placeholder").ifEmpty { null }
        val required = node.attrAsBoolean("required", true)
        val name = node.attrAsText("name").ifEmpty { null }
        val value = node.attrAsText("value").ifEmpty { null }

        return Phone(id, `class`, placeholder, required, name, value)
    }
}