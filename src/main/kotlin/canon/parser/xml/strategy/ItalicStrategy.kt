package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Italic
import org.w3c.dom.Node

class ItalicStrategy : AbstractParseStrategy<Italic>() {
    
    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Italic {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")

        return Italic(id, `class`, node.textContent)
    }
}