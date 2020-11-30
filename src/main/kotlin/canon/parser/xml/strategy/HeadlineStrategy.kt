package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Headline
import org.w3c.dom.Node

open class HeadlineStrategy : AbstractParseStrategy<Headline>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Headline {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }

        return Headline(id, `class`, node.textContent)
    }

}
