package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Transition
import org.w3c.dom.Node

open class TransitionStrategy : AbstractParseStrategy<Transition>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Transition {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val name = node.attrAsText("name").ifEmpty { null }
        val direction = node.attrAsText("direction").ifEmpty { null }
        val wrapped = node.attrAsText("wrapped").ifEmpty { null }

        return Transition(id, `class`, name, direction, wrapped, factory(node))
    }
}