package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Choice
import org.w3c.dom.Node

open class ChoiceStrategy : AbstractParseStrategy<Choice>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Choice {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")
        val text = node.attrAsText("text")
        val selected = node.attrAsText("selected", "false")

        return Choice(id, `class`, name, node.textContent, selected, factory(node))
    }
}