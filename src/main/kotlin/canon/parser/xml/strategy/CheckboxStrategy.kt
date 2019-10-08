package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Checkbox
import org.w3c.dom.Node

class CheckboxStrategy : AbstractParseStrategy<Checkbox>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Checkbox {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val value = node.attrAsText("value")
        val text = node.attrAsText("text")
        val name = node.attrAsText("name")
        val checked = node.attrAsText("checked")

        return Checkbox(id, `class`, value, text, name, checked)
    }

}