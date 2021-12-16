package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Col
import org.w3c.dom.Node

open class ColStrategy : AbstractParseStrategy<Col>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Col {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val ariaLabel = node.attrAsText("ariaLabel")

        return Col(id, `class`, ariaLabel, factory(node))
    }


}