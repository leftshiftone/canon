package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Choice
import org.w3c.dom.Node

class ChoiceStrategy : AbstractParseStrategy<Choice>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Choice {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val text = node.attrAsText("text")
        val selected = node.attrAsText("selected")

        return Choice(id, `class`, text, selected, factory(node))
    }


}