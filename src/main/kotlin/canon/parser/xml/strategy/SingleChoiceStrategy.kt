package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsText
import canon.model.SingleChoice
import org.w3c.dom.Node

class SingleChoiceStrategy : AbstractParseStrategy<SingleChoice>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): SingleChoice {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")
        val sieve = node.attrAsBoolean("sieve", false)

        return SingleChoice(id, `class`, name, sieve, factory(node))
    }
}