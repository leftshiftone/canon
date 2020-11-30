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
        val selected = node.attrAsText("selected", "false")

        var onlyText = true

        for(i in 0 until node.childNodes.length)
            if(node.childNodes.item(i).nodeName != "#text" && node.childNodes.item(i).nodeName != "#comment")
                onlyText = false

        return if (onlyText) {
            Choice(id, `class`, name, node.textContent, selected, factory(node))
        } else {
            Choice(id, `class`, name, null, selected, factory(node))
        }
    }
}
