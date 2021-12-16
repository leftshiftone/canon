package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsText
import canon.model.SingleChoice
import org.w3c.dom.Node

open class SingleChoiceStrategy : AbstractParseStrategy<SingleChoice>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): SingleChoice {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val ariaLabel = node.attrAsText("ariaLabel")
        val name = node.attrAsText("name")
        val sieve = node.attrAsBoolean("sieve", true)
        val required = node.attrAsBoolean("required", true)

        return SingleChoice(id = id, `class` = `class`, ariaLabel = ariaLabel, name =  name, sieve = sieve, required = required, renderables = factory(node))
    }
}