package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Container
import org.w3c.dom.Node

open class ContainerStrategy : AbstractParseStrategy<Container>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Container {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")

        return Container(id, `class`, name, factory(node))
    }
}