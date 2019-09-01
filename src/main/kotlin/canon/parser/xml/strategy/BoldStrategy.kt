package canon.parser.xml.strategy

import canon.extension.attrAsText
import canon.model.Bold
import canon.model.Text
import org.w3c.dom.Node

class BoldStrategy : AbstractParseStrategy<Bold>() {

    override fun parse(node: Node): Bold {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")

        return Bold(id, `class`, node.textContent)
    }

}
