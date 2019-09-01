package canon.parser.xml.strategy

import canon.extension.attrAsText
import canon.model.Headline
import canon.model.Text
import org.w3c.dom.Node

class HeadlineStrategy : AbstractParseStrategy<Headline>() {

    override fun parse(node: Node): Headline {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")

        return Headline(id, `class`, node.textContent)
    }

}
