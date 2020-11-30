package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsText
import canon.model.Email
import org.w3c.dom.Node

open class EmailStrategy : AbstractParseStrategy<Email>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Email {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val placeholder = node.attrAsText("placeholder").ifEmpty { null }
        val required = node.attrAsBoolean("required", false)
        val name = node.attrAsText("name").ifEmpty { null }
        val value = node.attrAsText("value").ifEmpty { null }

        return Email(id, `class`, placeholder, required, name, value)
    }

}
