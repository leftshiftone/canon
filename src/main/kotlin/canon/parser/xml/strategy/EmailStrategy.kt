package canon.parser.xml.strategy

import canon.extension.attrAsBoolean
import canon.extension.attrAsText
import canon.model.Email
import canon.model.Text
import org.w3c.dom.Node

class EmailStrategy : AbstractParseStrategy<Email>() {

    override fun parse(node: Node): Email {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val placeholder = node.attrAsText("placeholder")
        val required = node.attrAsBoolean("required", false)
        val name = node.attrAsText("name")

        return Email(id, `class`, placeholder, required, name, node.textContent)
    }

}
