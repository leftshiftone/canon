package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Text
import org.w3c.dom.Node

@Deprecated("Will be replaced with TextStrategy soon")
open class OldTextStrategy : AbstractParseStrategy<Text>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Text {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")

        return Text(id, `class`, node.textContent)
    }
}