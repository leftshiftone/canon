package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Suggestion
import org.w3c.dom.Node

open class SuggestionStrategy : AbstractParseStrategy<Suggestion>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Suggestion {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")
        val value = node.attrAsText("value")

        return Suggestion(id, `class`, node.textContent, name, value)
    }
}