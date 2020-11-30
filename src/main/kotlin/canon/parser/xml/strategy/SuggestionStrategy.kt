package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Suggestion
import org.w3c.dom.Node

open class SuggestionStrategy : AbstractParseStrategy<Suggestion>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Suggestion {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val name = node.attrAsText("name").ifEmpty { null }
        val value = node.attrAsText("value").ifEmpty { null }

        return Suggestion(id, `class`, node.textContent, name, value)
    }
}