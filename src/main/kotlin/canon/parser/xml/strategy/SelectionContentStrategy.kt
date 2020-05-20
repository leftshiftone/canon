package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.SelectionContent
import org.w3c.dom.Node

open class SelectionContentStrategy : AbstractParseStrategy<SelectionContent>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): SelectionContent {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")

        return SelectionContent(id, `class`, name, factory(node))
    }
}