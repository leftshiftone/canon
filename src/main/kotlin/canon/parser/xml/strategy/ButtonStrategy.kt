package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Button
import org.w3c.dom.Node

class ButtonStrategy : AbstractParseStrategy<Button>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Button {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val text = node.attrAsText("text")
        val name = node.attrAsText("name")
        val value = node.attrAsText("value")

        return Button(id, `class`, text, name, value)
    }
}