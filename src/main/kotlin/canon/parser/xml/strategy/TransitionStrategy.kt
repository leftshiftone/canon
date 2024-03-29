package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Transition
import org.w3c.dom.Node

open class TransitionStrategy : AbstractParseStrategy<Transition>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Transition {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val ariaLabel = node.attrAsText("ariaLabel")
        val name = node.attrAsText("name")
        val direction = node.attrAsText("direction")
        val wrapped = node.attrAsText("wrapped")

        return Transition(id, `class`, ariaLabel, name, direction, wrapped, factory(node))
    }
}