package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Text
import org.w3c.dom.Node

@Deprecated("Will be replaced with TextStrategy soon")
open class OldTextStrategy(val trim:Boolean = false) : AbstractParseStrategy<Text>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Text {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")

        if (trim)
            return Text(id, `class`, node.textContent.trim())
        else
            return Text(id, `class`, node.textContent)
    }
}
