package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Transition
import org.w3c.dom.Node

class TransitionStrategy : AbstractParseStrategy<Transition>() {

    override fun parse(node: Node, context: Map<String, Any?>, factory: (Node, Map<String, Any?>) -> List<IRenderable>): Transition {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")
        val direction = node.attrAsText("direction")
        val wrapped = node.attrAsText("wrapped")

        return Transition(id, `class`, name, direction, wrapped, factory(node, context))
    }
}