package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Suggestion
import org.w3c.dom.Node

class SuggestionStrategy : AbstractParseStrategy<Suggestion>() {

    override fun parse(node: Node, context: Map<String, Any?>, factory: (Node, Map<String, Any?>) -> List<IRenderable>): Suggestion {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")
        val value = node.attrAsText("value")

        return Suggestion(id, `class`, node.textContent, name, value)
    }
}