package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsText
import canon.model.MultipleChoice
import org.w3c.dom.Node

open class MultipleChoiceStrategy : AbstractParseStrategy<MultipleChoice>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): MultipleChoice {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val name = node.attrAsText("name").ifEmpty { null }
        val sieve = node.attrAsBoolean("sieve", true)
        val required = node.attrAsBoolean("required", true)

        return MultipleChoice(id, `class`, name, sieve, required, factory(node))
    }
}