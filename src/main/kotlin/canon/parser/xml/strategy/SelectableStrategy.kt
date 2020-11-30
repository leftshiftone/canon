package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Selectable
import org.w3c.dom.Node

open class SelectableStrategy : AbstractParseStrategy<Selectable>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Selectable {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val name = node.attrAsText("name").ifEmpty { null }

        return Selectable(id, `class`, name, factory(node))
    }
}