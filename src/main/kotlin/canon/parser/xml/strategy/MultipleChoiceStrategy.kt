package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsText
import canon.model.MultipleChoice
import org.w3c.dom.Node

class MultipleChoiceStrategy : AbstractParseStrategy<MultipleChoice>() {

    override fun parse(node: Node, context: Map<String, Any?>, factory: (Node, Map<String, Any?>) -> List<IRenderable>): MultipleChoice {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")
        val sieve = node.attrAsBoolean("sieve", false)

        return MultipleChoice(id, `class`, name, sieve, factory(node, context))
    }
}